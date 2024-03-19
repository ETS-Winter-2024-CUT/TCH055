import java.sql.*;
import java.util.Scanner;

import exceptions.AccesRefuseException;

/**
 * Implémentation simpliste d'un logiciel de gestion d'une banque. Elle vise
 * à illustrer l'utilisation de JDBC pour se connecter et interroger une base de
 * données.
 *
 * Elle permet également de mettre en évidence les risques d'une attaque par
 * "injection SQL". (utiliser "lapm' OR '1'='1" comme mot de passe ) et de
 * l'intérêt
 * des requêtes précompilées.
 *
 * @author anis
 */
public class Banque {
	public static void main(String[] args) {

		Scanner console = new Scanner(System.in);

		// Informations de connexion de l'usager
		String nomUtilisateur;
		String motPasse;

		System.out.println("Veuilez saisir votre nom d'utilisateur: ");
		nomUtilisateur = console.nextLine();

		System.out.println("Veuilez entrez votre mot de passe: ");
		motPasse = console.nextLine();

		Client clientAuthentifie = null;
		try {
			clientAuthentifie = Client.authentifier(nomUtilisateur, motPasse);
		} catch (AccesRefuseException e) {
			System.out.println(e.getMessage());
			console.close();
			System.exit(-1);
		}

		// On affiche la liste d'opérations
		try {
			// Chargement du driver JDBC
			Class.forName("oracle.jdbc.OracleDriver");

			// Création de la connexion
			// NDE: C'est une TRES MAUVAISE PRATIQUE de se connecter pour chaque
			// opération sous Oracle, car la connexion est particulièrement
			// coûteuse en performances. Dans votre Laboratoire, vous devez créer une
			// connexion
			// au début du programe, que vous utilisez tout au long du programme, pour ne la
			// fermer
			// qu'en fin de programme.
			Connection laConnectionJDBC = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + GestionBD.BD_URL_SERVEUR + ":" + GestionBD.BD_PORT + ":" + GestionBD.BD_SID,
					GestionBD.BD_UTILISATEUR, GestionBD.BD_MOT_PASSE);

			Statement stmt = laConnectionJDBC.createStatement();

			// On construit la requête SQL
			String requeteSQL = "SELECT num_operation, date_operation, montant, Operation.num_compte ";
			requeteSQL += "FROM Operation ";
			requeteSQL += "JOIN Compte ON Operation.num_compte = Compte.num_compte ";
			requeteSQL += "WHERE id_client = " + clientAuthentifie.getIdClient() + " ";
			requeteSQL += "ORDER BY Operation.num_compte, date_operation DESC";

			// Exécute la requête construite
			ResultSet resultats = stmt.executeQuery(requeteSQL);

			// On affiche toutes opérations
			while (resultats.next()) {
				String uneOperation = "" + resultats.getInt("num_compte") + "\t";
				uneOperation += resultats.getInt("num_operation") + "\t";
				uneOperation += resultats.getDate("date_operation") + "\t";
				uneOperation += resultats.getDouble("montant") + "$";

				System.out.println(uneOperation);
			}

			// On ferme la connexion JDBC
			laConnectionJDBC.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		console.close();
	}
}

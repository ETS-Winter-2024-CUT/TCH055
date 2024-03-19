import java.sql.*;

import exceptions.AccesRefuseException;

/**
 * Entité Client
 *
 * <p>
 * Classe d'objets clients persistants. L'instanciation d'un client se fait pas
 * new pour un nouveau client, ou en utilisant la méthode statique authentifier
 * pour un client existant.
 *
 * @author Anis Boubaker
 */
public class Client {

	// L'identifiant unique d'un client
	private int idClient;
	// Le nom de famille du client
	private String nom;
	// Le prénom du client
	private String prenom;
	// Détermine si l'instance du client a été obtenue suite à son authentification.
	private boolean estAuthentifie = false;

	public int getIdClient() {
		return idClient;
	}

	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 *
	 * Méthode fabrique qui recherche un Client en base de données ayant comme
	 * nom d'utilisateur et mot de passe ceux passés en paramètre.
	 * Si un tel client est trouvé, une instance Client correspondante est
	 * retournée.
	 * Sinon, un la méthode retourne <code>null</code>.
	 *
	 * @param nomUtilisateur le nom d'utilisateur unique
	 * @param motPasse       le mot de passe correspondant au nom d'usager.
	 * @return Une instance de Client <em>seulement</em> si le couple usager/mot de
	 *         passe existe.
	 * @throws AccesRefuseException si le couple usager/mot de passe n'existe pas.
	 */
	public static Client authentifier(String nomUtilisateur, String motPasse) throws AccesRefuseException {

		// Stocke le client qui correspond aux données d'identification fournis
		Client leClient = null;

		try {
			// NDE: Il serait plus judicieux d'avoir une méthode dans GestionBD qui
			// crée la connexion, plutôt de recopier ce code à chaque opération.
			// Chargement du driver JDBC
			Class.forName("oracle.jdbc.OracleDriver");

			// Création de la connexion
			Connection laConnectionJDBC = DriverManager.getConnection(
					"jdbc:oracle:thin:@" + GestionBD.BD_URL_SERVEUR + ":" + GestionBD.BD_PORT + ":" + GestionBD.BD_SID,
					GestionBD.BD_UTILISATEUR, GestionBD.BD_MOT_PASSE);

			// Trouver le Client qui correspond aux données d'authentification
			Statement requete = laConnectionJDBC.createStatement();

			// On construit la requête SQL dans une chaine de caractères, avant de
			// l'exécuter.
			String requeteSQL = "SELECT * FROM Client";
			requeteSQL += " WHERE nom_utilisateur='" + nomUtilisateur + "'";
			requeteSQL += " AND mot_passe='" + motPasse + "'";

			System.out.println(requeteSQL);

			ResultSet resultats = requete.executeQuery(requeteSQL);

			if (resultats.next()) { // On a trouvé l'usager
				leClient = new Client();
				leClient.setIdClient(resultats.getInt("id_client"));
				leClient.setPrenom(resultats.getString("prenom"));
				leClient.setNom(resultats.getString("nom"));
			} else { // nom d'usager introuvable ou le mot de passe ne correspond pas
				throw new AccesRefuseException(
						"Le couple nom d'utilisateur/mot de passe introuvable en base de données.");
			}

			// IMPORTANT: Ne pas oublier de fermer la connection là où on l'ouvre!
			laConnectionJDBC.close();

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return leClient;
	}

}

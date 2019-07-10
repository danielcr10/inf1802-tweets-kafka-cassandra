import com.datastax.driver.core.Session;

/**
 * Repository to handle the cassandra schema
 */
public class KeyspaceRepository {
    private Session session;

    public KeyspaceRepository(Session session) {
        this.session = session;
    }

    /**
     * Method used to create any keyspace - schema
     * @param keyspaceName the name of schema
     * @param repStrat the replication strategy
     * @param replicas the number of replicas
     */
    public void createKeyspace(String keyspaceName, String repStrat, int replicas) {
        StringBuilder sb = new StringBuilder("CREATE KEYSPACE IF NOT EXISTS ")
                .append(keyspaceName).append(" WITH replication = {").append("'class':'")
                .append(repStrat).append("', 'replication_factor':")
                .append(replicas).append("};");
        final String query = sb.toString();
        session.execute(query);
    }

    public void useKeyspace(String keyspace) {
        System.out.println("useKeyspace – init");
        session.execute("USE " + keyspace);
    }

    /**
     * Method used to delete the specified schema.
     * It results in the immediate, irreversable removal of the keyspace,
     * including all tables and data contained in the keyspace.
     *
     * @param keyspaceName the name of the keyspace to delete.
     */
    public void deleteKeyspace(String keyspaceName) {
        System.out.println("deleteKeyspace – init");
        StringBuilder sb = new StringBuilder("DROP KEYSPACE ").append(keyspaceName);

        final String query = sb.toString();
        System.out.println("deleteKeyspace – command: " + query.toUpperCase());
        session.execute(query);

        System.out.println("deleteKeyspace – end");

    }
}
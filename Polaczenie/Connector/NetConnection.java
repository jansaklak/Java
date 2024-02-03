public interface NetConnection {
	/**
	 * Metoda otwiera poĹÄczenie do serwera dostÄpnego protokoĹem TCP/IP pod adresem
	 * host i numerem portu TCP port.
	 *
	 * @param host adres IP lub nazwa komputera
	 * @param port numer portu, na ktĂłrym serwer oczekuje na poczÄczenie
	 */
	public void connect(String host, int port);
}

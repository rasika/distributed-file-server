package org.dc.file.search;

import java.io.IOException;
import java.util.Calendar;
import java.util.Map;
import java.util.Scanner;

public class DCNode {

    public static void main(String[] args) throws IOException {
        Store.getInstance().displayFilesList();
        long username = Calendar.getInstance().getTimeInMillis() % 1000000000;
        Peer localPeer = MessageUtils.init(username);
        MessageUtils.sendTCPMessage(Constants.BOOTSTRAP_SERVER,
                                    Constants.BOOTSTRAP_PORT,
                                    "REG " + localPeer.getIp() + " " + localPeer.getPort() + " " + username);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "exit":
                    for (Map.Entry<String, Peer> entry : Store.getInstance().getPeerMap().entrySet()) {
                        Peer peer = entry.getValue();
                        MessageUtils.sendUDPMessage(peer.getIp(),
                                                    peer.getPort(),
                                                    "LEAVE " + localPeer.getIp() + " " + localPeer.getPort());
                    }
                    MessageUtils.sendTCPMessage(Constants.BOOTSTRAP_SERVER,
                                                Constants.BOOTSTRAP_PORT,
                                                "UNREG " + localPeer.getIp() + " " + localPeer.getPort() + " " + username);
                    break;
                case "peers":
                    Store.getInstance().displayPeerList();
                    break;
                case "files":
                    Store.getInstance().displayFilesList();
                    break;
                case "search":
                    System.out.print("Enter key: ");
                    String key = scanner.nextLine();
                    for (Map.Entry<String, Peer> entry : Store.getInstance().getPeerMap().entrySet()) {
                        SearchRequest searchRequest = new SearchRequest(Calendar.getInstance().getTimeInMillis(),
                                                                        key, 2, localPeer);
                        Store.setMySearchRequest(searchRequest);
                        Store.getInstance().addSearchRequest(searchRequest);
                        Peer peer = entry.getValue();
                        MessageUtils.sendUDPMessage(peer.getIp(),
                                                    peer.getPort(),
                                                    "SER " + localPeer.getIp() + " " + localPeer.getPort()
                                                    + " \"" + key + "\" 2");
                    }
                    break;
                case "requests":
                    Store.getInstance().displaySearchRequestsList();
                    break;
                default:
                    System.err.println("Invalid input");
            }
        }
    }

}

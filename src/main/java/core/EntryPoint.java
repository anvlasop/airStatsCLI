package core;

import core.isolatotion.UserIsolatorProcess;

public class EntryPoint {

    public void start() {
        startIsolateProcess();
    }

    private void startIsolateProcess() {
        UserIsolatorProcess userIsolatorProcess = new UserIsolatorProcess();
        userIsolatorProcess.startIsolation();
    }






}

package com.prayagupd.cassava.data.api;

public class CassavaErrors extends Exception {

    public static class CassavaError extends Exception {
        public CassavaError(String errorMessage, Throwable t) {
            super(errorMessage, t);
        }
    }

    public static class CassavaExitError extends RuntimeException {
        public CassavaExitError(String errorMessage, Throwable t) {
            super(errorMessage, t);
        }
    }

}

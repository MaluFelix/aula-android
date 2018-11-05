package br.com.sevenestoque.sevenestoque;

class PrefsT {
    private static final PrefsT ourInstance = new PrefsT();

    static PrefsT getInstance() {
        return ourInstance;
    }

    private PrefsT() {


    }
}

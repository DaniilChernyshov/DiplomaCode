public class LocalStorage {
    private static LocalStorage storage;

    private LocalStorage(){

    }

    public static LocalStorage getInstance(){
        if (storage == null){
            storage = new LocalStorage();
        }
        return storage;
    }

    public void saveResult(SharedPreferences preferences, int sum, String key){
        SharedPreferences.Editor editor = preferences.edit();
        if (Integer.valueOf(preferences.getString(key, "0")) < sum){
            editor.putString(key, String.valueOf(sum));
            editor.commit();
        }
    }

    public String getBestResult(SharedPreferences preferences, String key){
        return preferences.getString(key, "0");
    }
}

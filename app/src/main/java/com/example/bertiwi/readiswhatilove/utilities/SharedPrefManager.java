package com.example.bertiwi.readiswhatilove.utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Clase para facilitar la interacción con {@link SharedPreferences} de Android.
 *
 * @author Nil Ordoñez
 */
public class SharedPrefManager {

    /** El nombre de las {@link SharedPreferences} que usa la aplicación */
    private static final String SHARED_PREF_NAME = "codingsharedpref";


    /** La key para almacenar el access token en {@link SharedPreferences}, que es "{@value}" */
    private static final String KEY_TOKEN = "keytoken";

    /** Instancia de {@link SharedPrefManager} para implementar el patron Singleton */
    private static SharedPrefManager mInstance;

    /** {@link android.content.Context} para acceder a las Shared Preferences */
    private static Context mContext;


    /**
     * Constructor. Privado ya que implementa patrón Singleton
     *
     * @param context {@link Context} para obtener las {@link SharedPreferences}
     */
    private SharedPrefManager(Context context) {
        mContext = context;
    }


    /**
     * Obtiene una instancia de SharedPrefManager.
     * Es sincronized ya que se accede desde threads en la aplicacción
     *
     * @param context {@link Context} para obtener las {@link SharedPreferences}
     * @return Instancia de la clase.
     */
    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }


    /**
     * Obtiene de {@link SharedPreferences} el access token que se ha guardado.
     * @return Access token del usuario
     */
    public String getToken() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_TOKEN, "");
    }

    /**
     * Guardar en {@link SharedPreferences} el access token.
     * @param token El access token a almacenar
     */
    public void setToken(String token) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_TOKEN, token);
        editor.apply();
    }

}

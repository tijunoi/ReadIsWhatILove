package com.example.bertiwi.readiswhatilove.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Implementación del patrón Singleton para la {@link RequestQueue} de {@link Volley}
 *
 * @author Bertiwi
 */

public class VolleySingleton {

    // Atributos
    /** Instancia de {@link VolleySingleton} para implementar el patron Singleton */
    private static VolleySingleton singleton;

    /**
     * {@link RequestQueue} de {@link Volley} que se usa para las requests.
     */
    private RequestQueue requestQueue;

    /** {@link android.content.Context} para acceder a Voley con el context de la aplicación */
    private static Context mCtx;

    /**
     * Constructor. Privado ya que implementa patrón Singleton
     *
     * @param context {@link Context} para obtener la {@link RequestQueue} de {@link Volley}
     */
    private VolleySingleton(Context context) {
        mCtx = context;
        requestQueue = getRequestQueue();

    }

    /**
     * Obtiene una instancia de VolleySingleton.
     * Es sincronized ya que se accede desde threads en la aplicación
     *
     * @param context {@link Context} para obtener la {@link RequestQueue} de {@link Volley}
     * @return Instancia de la clase.
     */
    public static synchronized VolleySingleton getInstance(Context context) {
        if (singleton == null) {
            singleton = new VolleySingleton(context);
        }
        return singleton;
    }

    /**
     * Genera una {@link RequestQueue} y la devuelve.
     * @return Una request queue de Volley
     */
    private RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        return requestQueue;
    }

    /**
     * Añade una Volley Request a la RequestQueue. Se ejecitará la request en el orden de inclusión, como decide {@link Volley}
     * @param req La request a añadir a la queue.
     */
    public void addToRequestQueue(Request req) {

        req.setRetryPolicy(new DefaultRetryPolicy(
                20*1000,
                -1,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        getRequestQueue().add(req);
    }

}

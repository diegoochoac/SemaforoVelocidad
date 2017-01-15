package com.ochoa.ochoa.semaforo.Fragmentos;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import com.ochoa.ochoa.semaforo.MainActivity;
import com.ochoa.ochoa.semaforo.R;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class FragmentoInicio extends Fragment implements LocationListener {


    public TextView velocidad, distancia, distanciaM, ubicacion, estado;

    public TableRow Tbdistancia, Tbvelocidad;

    private LocationManager mLocationManager;

    private Location fotomulta1;
    private Location fotomulta2;
    private Location fotomulta3;
    private Location fotomulta4;
    private Location fotomulta5;
    private Location fotomulta6;
    private Location fotomulta7;
    private Location fotomulta8;
    private Location fotomulta9;
    private Location fotomulta10;

    List<Location> fotomultas;

    private static final String TAG = "LocationFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private String Velocidad;
    private String Distancia;


    private OnFragmentInteractionListener mListener = null;

    MediaPlayer mp;

    public FragmentoInicio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoInicio.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoInicio newInstance(String param1, String param2) {
        FragmentoInicio fragment = new FragmentoInicio();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragmento_inicio, container, false);

        velocidad = (TextView) view.findViewById(R.id.txtVelocidad);
        distancia = (TextView) view.findViewById(R.id.txtDistancia);
        distanciaM = (TextView) view.findViewById(R.id.textViewMetro);
        ubicacion = (TextView) view.findViewById(R.id.txtUbicacion);
        estado =(TextView) view.findViewById(R.id.txtEstado);

        distancia.setVisibility(View.INVISIBLE);
        distanciaM.setVisibility(View.INVISIBLE);

        Tbdistancia = (TableRow) view.findViewById(R.id.TableDistancia);
        Tbvelocidad = (TableRow) view.findViewById(R.id.TableVelocidad);

        mp = MediaPlayer.create(getContext(),R.raw.alerta);

        fotomultas = new ArrayList<>();

        fotomulta1 = new Location("Foto Canchas");
        fotomulta1.setLatitude(3.422334);
        fotomulta1.setLongitude(-76.538268);

        fotomulta2 = new Location("Foto Don Bosco");
        fotomulta2.setLatitude(3.442769);
        fotomulta2.setLongitude(-76.532022);

        fotomulta3 = new Location("Foto Calle 7");
        fotomulta3.setLatitude(3.431329);
        fotomulta3.setLongitude(-76.537576);

        fotomulta4 = new Location("Ingenio");
        fotomulta4.setLatitude(3.387351);
        fotomulta4.setLongitude(-76.532458);

        fotomulta5 = new Location("FOTO CASA");
        fotomulta5.setLatitude(3.414307);
        fotomulta5.setLongitude(-76.523944);

        fotomultas.add(0,fotomulta1);
        fotomultas.add(1,fotomulta2);
        fotomultas.add(2,fotomulta3);
        fotomultas.add(3,fotomulta4);
        fotomultas.add(4,fotomulta5);


        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
            mLocationManager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + "El Activity debe implementar la interfaz FragmentIterationListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
        mLocationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, this);
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, this);

        velocidad.setText(Velocidad);
        distancia.setText(Distancia);
    }

    @Override
    public void onPause() {
        super.onPause();

        Log.i(TAG, "onPause");
        if (ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationManager.removeUpdates(this);
    }


    @Override
    public void onLocationChanged(Location location) {
        Log.i(TAG, String.valueOf(location.getLatitude()));
        Log.i(TAG, String.valueOf(location.getLongitude()));

        DecimalFormat df = new DecimalFormat("0.0");
        Velocidad = String.valueOf(df.format(location.getSpeed()*3.6));
        setLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        Log.i(TAG, "Provider " + s + " has now status: " + i);
        ubicacion.setText("Provider " + s + " has now status: " + i);
    }

    @Override
    public void onProviderEnabled(String s) {
        Log.i(TAG, "Provider " + s + " is enabled");
        ubicacion.setText("Provider " + s + " is enabled");
    }

    @Override
    public void onProviderDisabled(String s) {
        Log.i(TAG, "Provider " + s + " is disabled");
        ubicacion.setText("Provider " + s + " is disabled");
    }

    public void setLocation(Location loc) {

        velocidad.setText(Velocidad);

        String MensajeEstado="";
        String MensajeDistancia="";
        for(int j = 0; j<= fotomultas.size()-1; j++){

            float distanceInMeters = loc.distanceTo(fotomultas.get(j));
            DecimalFormat df = new DecimalFormat("0.0");

            if (distanceInMeters <= 1000){

                mp.start();
                Distancia = String.valueOf(df.format(distanceInMeters));
                distancia.setText(Distancia);
                distancia.setVisibility(View.VISIBLE);
                distanciaM.setVisibility(View.VISIBLE);

                //Obtener la direcci—n de la calle a partir de la latitud y la longitud
                if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
                    try {
                        Geocoder geocoder = new Geocoder(this.getContext(), Locale.getDefault());
                        List<Address> list = geocoder.getFromLocation(loc.getLatitude(), loc.getLongitude(), 1);
                        if (!list.isEmpty()) {
                            Address address = list.get(0);
                            ubicacion.setText(address.getAddressLine(0));
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(distanceInMeters<=500){

                    MensajeEstado = MensajeEstado+(" FOTOMULTA "+
                            "\n"+fotomultas.get(j).getProvider());
                    estado.setBackgroundColor(getResources().getColor(R.color.GrisTransparente));
                    Tbdistancia.setBackgroundColor(getResources().getColor(R.color.GrisTransparente));
                }
                if (loc.getSpeed()*3.6 >= 60){
                    estado.setBackgroundColor(getResources().getColor(R.color.GrisTransparente));
                    Tbvelocidad.setBackgroundColor(getResources().getColor(R.color.RojoTransparente));
                }
                if (loc.getSpeed()*3.6 <= 60){
                    estado.setBackgroundColor(getResources().getColor(R.color.GrisTransparente));
                    Tbvelocidad.setBackgroundColor(getResources().getColor(R.color.VerdeTransparente));
                }
            }
        }

        if(MensajeEstado !=""){
            estado.setText(MensajeEstado);

        }else{

            estado.setText("NO HAY FOTOMULTAS");
            estado.setBackgroundColor(Color.WHITE);
            Tbdistancia.setBackgroundColor(Color.WHITE);
            Tbvelocidad.setBackgroundColor(Color.WHITE);

            distancia.setVisibility(View.INVISIBLE);
            distanciaM.setVisibility(View.INVISIBLE);
        }

    }
}

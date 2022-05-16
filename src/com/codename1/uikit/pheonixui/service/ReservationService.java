package com.codename1.uikit.pheonixui.service;

import com.codename1.components.ToastBar;
import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import com.codename1.uikit.pheonixui.model.Reservation;
import com.codename1.uikit.pheonixui.utils.Statics;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ReservationService {
    public ConnectionRequest request;
    public boolean resultOK;
    private ArrayList<Reservation> reservations = new ArrayList<>();
    private static ReservationService instance;


    private ReservationService() {
        request = new ConnectionRequest();
    }

    public static ReservationService getInstance() {
        if (instance == null)
            instance = new ReservationService();
        return instance;
    }

    public boolean addReservation(Reservation reserve){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Making a new reservation ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/reservation/ajouter/reservation";
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);

        request.addArgument("adresse",reserve.getAdresse());
        request.addArgument("image",reserve.getImage());
        request.addArgument("nomResto",reserve.getNomResto());
        request.addArgument("dateReservation",String.valueOf(reserve.getDateReservation()));
        //request.addArgument(,reserve.getArchive());
        //request.addArgument(,reserve.getIdRestaurant());
        request.addArgument("nbplaceReservation",String.valueOf(reserve.getNbplaceReservation()));
        request.addArgument("idUser",String.valueOf(reserve.getIdUser()));


        System.out.println(request.getRequestBody());

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                System.out.println("error 1 here");
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        status.clear();
        return resultOK;
    }

    public ArrayList<Reservation> getReservations(){
        System.out.println("In Get Reservations Method");
        System.out.println("Got Id");
        request = new ConnectionRequest();
        String url = Statics.BASE_URL+"/afficher/reservation";
        request.setUrl(url);
        request.setPost(false);
        System.out.println("Configured request : "+request.getRequestBody());

        request.addResponseListener((evt -> {
            try {
                System.out.println("GetReservations::ResponseListener");
                reservations = parseReservation(new String(request.getResponseData()));
            } catch (IOException | IllegalAccessException | NoSuchFieldException | ParseException e) {
                e.printStackTrace();
            }
        }));
        NetworkManager.getInstance().addToQueueAndWait(request);
        return reservations;
    }

    private ArrayList<Reservation> parseReservation(String jsonText) throws IOException, NoSuchFieldException, IllegalAccessException, ParseException {
        System.out.println("In Parse Product Method");
        reservations = new ArrayList<>();
        JSONParser j = new JSONParser();
        Map<String,Object> reservationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
        List<Map<String,Object>> list = (List<Map<String, Object>>) reservationListJson.get("root");
        for (Map<String,Object> obj : list){

            float reservationIdF = Float.parseFloat(obj.get("idReservation").toString());
            int reservationId = Integer.parseInt(String.valueOf((int) reservationIdF));
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(obj.get("dateReservation").toString());
            String idUser = obj.get("idUser").toString();
            String archive = obj.get("archive").toString();
            String nomResto = obj.get("nomResto").toString();
            String adresse = obj.get("adresse").toString();
//            String idRestaurant = obj.get("idRestaurant").toString();


            Reservation reservation = new Reservation();
            reservation.setIdReservation(reservationId);
            reservation.setDateReservation(date);
            reservation.setIdUser(idUser);
            reservation.setArchive(archive);
            reservation.setNomResto(nomResto);
            reservation.setAdresse(adresse);
//            reservation.setIdRestaurant(idRestaurant);
            reservations.add(reservation);
        }


        return reservations;
    }

    public boolean deleteReservation(int id){
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Deleting your Reservation ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/delete/reservation/"+id;
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(false);
        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        status.clear();

        return resultOK;
    }

    public boolean updateReservation(Reservation reserve) {
        ToastBar.Status status = ToastBar.getInstance().createStatus();
        status.setMessage("Updating Your Product ...");
        status.setShowProgressIndicator(true);
        status.show();
        String url = Statics.BASE_URL+"/modifier/reservation/"+reserve.getIdReservation();
        request = new ConnectionRequest();
        request.setUrl(url);
        request.setPost(true);

        request.addArgument("adresse",reserve.getAdresse());
        request.addArgument("image",reserve.getImage());
        request.addArgument("nomResto",reserve.getNomResto());
        request.addArgument("dateReservation",String.valueOf(reserve.getDateReservation()));
        request.addArgument("nbplaceReservation",String.valueOf(reserve.getNbplaceReservation()));
        request.addArgument("idUser",String.valueOf(reserve.getIdUser()));

        request.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = request.getResponseCode() == 200;
                request.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(request);
        status.clear();
        return resultOK;
    }
}

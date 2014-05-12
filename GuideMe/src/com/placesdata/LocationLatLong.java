package com.placesdata;

import java.util.HashMap;

import com.google.android.gms.maps.model.LatLng;

public class LocationLatLong {
	
	public static final LatLng CITADEL_OF_QAITBAY_LL = new LatLng(31.214011,29.885638);//1
    public static final LatLng EL_BOSEARY_LL = new LatLng(31.205707,29.882856);//2
    public static final LatLng ABU_EL_ABBAS_LL = new LatLng(31.2056978,29.8827702);//3
    public static final LatLng ROYAL_JEWELRY_MUSEUM_LL = new LatLng(31.240885,29.963092);//4
    public static final LatLng MOSQUE_TERBANA_LL = new LatLng(31.2027697,29.8810959);//5
    public static final LatLng EL_SHORBAGY_LL = new LatLng(31.2022803,29.8843348);//6
    public static final LatLng ALI_BEK_GENENA_MOSQUE_LL = new LatLng(31.194182,29.8865369);//7
    public static final LatLng ABO_ALI_LL = new LatLng(31.194631,29.885899);//8
    public static final LatLng COM_NAADDOURH_LL = new LatLng(31.193291,29.887848);//9
    public static final LatLng SAYED_DARWISH_THEATRE_LL = new LatLng(31.201532,29.901055);//10
    public static final LatLng AL_TAHONAA_LL = new LatLng(31.2796842,30.0111568);//11
    public static final LatLng TABIA_KUSA_PASHA_LL = new LatLng(31.324485,30.063996);//12
	public static final LatLng BIBLIOTHECA_ALEX_LL = new LatLng(31.20887,29.909201);//13
	public static final LatLng ROMANIAN_AMPHTHEATER_LL = new LatLng(31.1798419,29.8913347);//14
	public static final LatLng ANTINYONADS_LL = new LatLng(31.202006,29.952373);//15
	public static final LatLng MONTAZA_PALACE_LL = new LatLng(31.288495,30.015969);//16
	public static final LatLng KASER_RAS_AL_TIN_LL = new LatLng(31.2055789,29.8782359);//17
	public static final LatLng ALEXANDRIA_NATIONAL_MUSEUM_LL = new LatLng(31.201532,29.901055);//18
	public static final LatLng THE_GRAECO_ROMAN_MUSEUM_LL = new LatLng(31.198468,29.907462);//19
	public static final LatLng POMPEYS_PILLAR_LL = new LatLng(31.1825,29.8964);//20
	public static final LatLng KOOM_EL_SHAFA_HISTORIC_CEMETERY_LL = new LatLng(31.1798419,29.8913347);//21
	
	public static HashMap<String, LatLng> getAllLongLat(){
		HashMap<String, LatLng> places_latLong = new HashMap<String, LatLng>();
        
		places_latLong.put("Roman Amphitheatre",ROMANIAN_AMPHTHEATER_LL);
		places_latLong.put("Catacombs of Kom el Shoqafa", KOOM_EL_SHAFA_HISTORIC_CEMETERY_LL);
		places_latLong.put("Pompey Pillar", POMPEYS_PILLAR_LL);
		places_latLong.put("Citadel of Quitbay", CITADEL_OF_QAITBAY_LL);
		places_latLong.put("El Bosery Mosque", EL_BOSEARY_LL);
		places_latLong.put("Abo El Abbas", ABU_EL_ABBAS_LL);
		places_latLong.put("Terbana Mosque", MOSQUE_TERBANA_LL);
		places_latLong.put("Sayed Darwish Theatre", SAYED_DARWISH_THEATRE_LL);
		places_latLong.put("Kom Naddourah", COM_NAADDOURH_LL);	
		places_latLong.put("El Shorbagy Mosque", EL_SHORBAGY_LL);
		places_latLong.put("Ali Bek Genena Mosque", ALI_BEK_GENENA_MOSQUE_LL);
		places_latLong.put("Abo Ali Mosque", ABO_ALI_LL);
		places_latLong.put("The Graeco Roman Museum", THE_GRAECO_ROMAN_MUSEUM_LL);
		places_latLong.put("Alexandria National Muesum", ALEXANDRIA_NATIONAL_MUSEUM_LL);
		places_latLong.put("Royal Jewelry musuem", ROYAL_JEWELRY_MUSEUM_LL);
		places_latLong.put("Bibliotheca Alexandria", BIBLIOTHECA_ALEX_LL);
		places_latLong.put("Montaza Palace", MONTAZA_PALACE_LL);
		places_latLong.put("Kasr Ras El Tin", KASER_RAS_AL_TIN_LL);
		places_latLong.put("Antoniades", ANTINYONADS_LL);
		places_latLong.put("Al Tahonaa", AL_TAHONAA_LL);
		places_latLong.put("Tabia Kusa Pasha", TABIA_KUSA_PASHA_LL);
        
		return places_latLong;
	}
}

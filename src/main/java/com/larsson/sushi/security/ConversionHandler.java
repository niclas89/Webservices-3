package com.larsson.sushi.security;

import com.fasterxml.jackson.databind.JsonNode;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalTime;
import java.util.ArrayList;

@Component
public class ConversionHandler {

    private static final Logger conversionLogger = Logger.getLogger(ConversionHandler.class);


    private RestTemplate restTemplate = new RestTemplate();

    private LinkedMultiValueMap<String,BigDecimal> conversionRates;

    private LocalTime lastUpdate;

    private static ConversionHandler instance = null;

    public static synchronized ConversionHandler getInstance(){
        if(instance == null){
            instance = new ConversionHandler();
        }
        return instance;
    }

    private ConversionHandler() {
        conversionRates = new LinkedMultiValueMap<>();
        conversionRates.add("SEK",BigDecimal.ONE);
    }


    public BigDecimal getRate(String currencyCode){
        LocalTime currentTime = LocalTime.now();
        if(lastUpdate == null){
            updateRates();
            return conversionRates.getFirst(currencyCode);
        }else if(currentTime.toSecondOfDay() > lastUpdate.toSecondOfDay()+900){
          updateRates();
        }

        if(currentTime.toSecondOfDay() > lastUpdate.toSecondOfDay()+900){
            updateRates();
        }
        return conversionRates.getFirst(currencyCode);
    }


    public void updateRates() {
        ArrayList<String> symbols2 = new ArrayList<>();
        String symbols= "";
        JsonNode response = null;
        Boolean success = false;
        for(String symbol: conversionRates.keySet()){
            symbols += symbol;
            symbols2.add(symbol);
        }
            try {
                 response = restTemplate.getForObject("http://api.exchangeratesapi.io/v1/latest?access_key=74563b664fec86e81170225d315415bf&base=EUR&symbols="+symbols, JsonNode.class);
                 success = true;
            } catch (Exception exception) {
                exception.printStackTrace();
            }


        if(success) {
            for(String symbol: conversionRates.keySet()) {
                String amount = String.valueOf(response.get("rates").get(symbol));
                BigDecimal conversion = new BigDecimal(amount);
                conversionRates.getFirst(symbol).add(conversion);
                conversionRates.remove(symbol);
                conversionRates.add(symbol,conversion);
            }
            lastUpdate = LocalTime.now();
            conversionLogger.info("Updated conversion rates");

        }else{
            conversionLogger.info("Failed to update rates");
        }

    }

}



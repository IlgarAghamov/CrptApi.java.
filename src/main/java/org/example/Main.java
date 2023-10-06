package org.example;

import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        CrptApi crptApi = new CrptApi(TimeUnit.SECONDS, 5); // Пример: не более 5 запросов в секунду

        for (int i = 0; i < 10; i++) {
            String documentJson = "{\"description\": {\"participantInn\": \"string\"}, \"doc_id\": \"string\", " +
                    "\"doc_status\": \"string\", \"doc_type\": \"LP_INTRODUCE_GOODS\", " +
                    "\"importRequest\": true, \"owner_inn\": \"string\", \"participant_inn\": \"string\", " +
                    "\"producer_inn\": \"string\", \"production_date\": \"2020-01-23\", " +
                    "\"production_type\": \"string\", \"products\": [ { \"certificate_document\": \"string\", " +
                    "\"certificate_document_date\": \"2020-01-23\", \"certificate_document_number\": \"string\", " +
                    "\"owner_inn\": \"string\", \"producer_inn\": \"string\", \"production_date\": \"2020-01-23\", " +
                    "\"tnved_code\": \"string\", \"uit_code\": \"string\", \"uitu_code\": \"string\" } ], " +
                    "\"reg_date\": \"2020-01-23\", \"reg_number\": \"string\"}";

            String signature = "sample_signature";

            crptApi.createDocument(documentJson, signature);
        }
    }
}
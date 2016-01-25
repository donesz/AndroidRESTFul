package com.example.lordzabrilon.myapplication;

/**
 * Created by LordZabrilon on 1/23/16.
 * obeto de respuesta para una peticion JSON a 'http://rest-service.guides.spring.io/greeting'
 */
public class ObjResponse {
    private String id;
    private String content;

    public String getId() {
        return this.id;
    }

    public String getContent() {
        return this.content;
    }
}

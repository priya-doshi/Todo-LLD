package utils;

import java.util.UUID;

public class Utility {

    public static String generateNextTaskId(){
        return UUID.randomUUID().toString();
    }
}

package com.mattjbishop.droptest.hal;

/**
 * Created by matt on 08/08/2014.
 */
public class Views {
    public static class HideEmbedded { } // makes sure the embedded resources are not directly rendered in JSON
    public static class HideLinks { } // makes sure any links are not directly rendered in JSON
    public static class HAL { } // default view - does nothing other than make sure the other views are not used
}

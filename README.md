Hack Week Notes Day 1
===============

Installation
----
* Does everyone have Android Studio installed?
	* If not, download the [Android SDK Bundle for Android Studio](http://developer.android.com/sdk/installing/index.html).
* Create an emulator in Android Studio
* Make sure that everything is working and that you can compile an Android app with Android Studio
* This is a more comprehensive installation guide: [Railsbridge Android Installation](https://docs.google.com/presentation/d/1iD0sMc-qIG80yZ1AQfDU5nxSAl3Xe4nx-2W_g9yzMSM/edit#slide=id.p)

Structure of an Android App
---
Walk through the structure of a typical Android app by creating a new project in Android Studio

Some important areas to mention:

* The manifest
* Activities
* Res directory
	* drawable
	*  layout
	* values
		* strings
		* colors
		

Learning Android Layout
-----
* Android layouts are XML-based, no one actually uses the designer feature in Android Studio!
* Start by explaining what a textview is (there should be a hello world textview on the screen)

* Immediately switch to a linear layout and set the orientation to vertical (we'll get to relative layout later)
* Play around with textview and some other type of views to get them confortable with how to layout views
	*  Set color/margin/other attributes for the views

Creating the Splash Screen Layout
-----
* Show an image of what we want to create (an icon, with text underneath)
* Let's start with the image, create an ImageView and set the src
* Now let's add the textview underneath
* But we need to center these two views both horizontally and vertically within the screen?
	*  To do this, we need a relative layout. Explain how relative layouts differ, and then add a relative layout with a nested linear layout. The code should look something like this:

```
    <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                    xmlns:tools="http://schemas.android.com/tools"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent"
                    android:paddingLeft="@dimen/activity_horizontal_margin"
                    android:paddingRight="@dimen/activity_horizontal_margin"
                    android:paddingTop="@dimen/activity_vertical_margin"
                    android:paddingBottom="@dimen/activity_vertical_margin"
                    android:background="#5089DA"
                    tools:context="pomona.android.aloke.weather.SplashActivity">
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:orientation="vertical"
            android:layout_centerVertical="true"
            android:layout_centerHorizontal="true">

            <ImageView
                android:src="@drawable/weather"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"/>
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:id="@+id/tvGetWeather"
                android:text="@string/get_weather_text"
                android:textColor="@android:color/white"
                android:layout_gravity="center_horizontal"
                android:textSize="20sp"/>
        </LinearLayout>

    </RelativeLayout>
```

Tying in the Activity
----
At this point, we can move to `SplashActivity` and talk about how to hook up the views to the activity. This is probably the place to talk about the **activity lifecycle** and the associated methods that would be in `SplashActivity`. 

First start by talking about how to get the `tvGetWeather` textView by using `findViewById`. 

Maybe change the color or another property so people can see you can also change/create stuff on the Java end as well?

At this point, let's create an `onClickListener` for `tvGetWeather`. Make a simple toast when the user clicks on the textview so people understand how the onClickListener works.

Create the New Activity/Using Intents
---
At this point, create a new activity, called `WeatherActivity` that will hold all the content for the actual weather page. Show them what this screen will look like. 

Once you've created it (it can still just have the helloworld text), go back to the `SplashActivity` and create an **Intent** that links to the `WeatherActivity` in the `onClickListener` of `tvGetWeather`	. Explain what an Intent is and how it's the link between two different activities (screens) in an Android app.


Create the layout for the Weather Activity
---
Go into `activity_weather`, and start creating the layout for the actual weather app. The final layout for this page is:

```
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
                xmlns:tools="http://schemas.android.com/tools"
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:orientation="vertical"
                android:paddingLeft="@dimen/activity_horizontal_margin"
                android:paddingRight="@dimen/activity_horizontal_margin"
                android:paddingTop="@dimen/activity_vertical_margin"
                android:paddingBottom="@dimen/activity_vertical_margin"
                tools:context=".MainActivity">
    <EditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/etCity"
        android:hint="@string/edit_text_city_hint"/>

    <Button
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/btnSubmit"
        android:text="@string/button_get_weather_text"/>

    <TextView
        android:text="@string/location_text"
        android:id="@+id/tvCurrentCity"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"/>

    <TextView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:textSize="100sp"
        android:gravity="center_horizontal"
        android:textColor="@android:color/holo_blue_dark"
        android:id="@+id/tvCurrentTemp"
        android:text="@string/current_temp_default_text"/>

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:gravity="center_horizontal">

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:id="@+id/tvHigh"
            android:text="@string/temp_high_label"/>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text=" / "
            android:id="@+id/tvSlash"
            android:layout_toRightOf="@id/tvHigh"/>

        <TextView
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_toRightOf="@id/tvSlash"
            android:id="@+id/tvLow"
            android:text="@string/temp_low_label"/>
    </RelativeLayout>
</LinearLayout>

```		



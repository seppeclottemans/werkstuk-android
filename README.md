# werkstuk-android

# Resources used:

- stackoverflow: https://stackoverflow.com
- andoid developers documentation: https://developer.android.com
- Android Jetpack tutorials: https://developer.android.com/jetpack
- tutorial used for basic Room and live data usage => Room + ViewModel + LiveData + RecyclerView (MVVM): https://www.youtube.com/channel/UC_Fh8kvtkVPkeihBs42jGcA (20/05/2020)
- various other youtube tutorials
- Android charts used: https://github.com/PhilJay/MPAndroidChart
- For the navigation basics I used a Bottom Navigation Activity.
- further resource references are in the code.

# Additional info
- The accelerometer which is used to register the phone drops has two references in the code in MainActivity.java 
    -  for testing the code in the emulator use the if (loAccelerationReader <= 6.0) (MainActivity.java line: 89)
    -  for usage with a real phone use if (ldAccRound > 0.3d && ldAccRound < 0.5d) (MainActivity.java line: 89)
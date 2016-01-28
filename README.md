# Android Studio Issue Repo

This is a sample app for displaying a particlar issue with Android Studio to the Android tools team. 

### Overview

When using Maven dependencies within Android Studio, you may use libraries deployed either as `.aar` files or `.jar` files. It's very useful during development to be able to step right into library code to view the source and javadocs. **Currently, this is only supported for `.jar`-based libraries, and not `.aar.` libraries.**

This is a sample Android application project created using Android Studio 2.0 Preview 8 which has only been modified to:

1. Use a custom Maven endpoint where I've deployed the sample library in both jar and aar form
2. Add the aar/jar library as dependencies in `build.gradle`
3. `MainActivity` has been altered to reference the library, to display when source/docs are and aren't attached.

### History

This issue has been brought to the attention of the tools team in the past, but has unfortunately been marked as fixed and released. This repository is an effort to show that this issue is still present.

This is the past issue report:
https://code.google.com/p/android/issues/detail?id=74203

### Steps to Reproduce

1. Import the enclosed Android Application into Android Studio 2.0 Preview 8, on OS X 10.11.3 (Studio version and OS X version don't actually matter, the issue will be present regardless)
2. Open [app/build.gradle](https://github.com/samdozor/sample-app-for-tools-team/blob/master/app/build.gradle), and note that the current MParticle dependency is `compile 'com.mparticle:android-core-test:4.2.1@aar'`
3. Open [MainActivity.java](https://github.com/samdozor/sample-app-for-tools-team/blob/master/app/src/main/java/com/dozor/sample/bug/MainActivity.java) and Command-Click into the `MParticle` class within `onCreate`. 

*Expected Result*:
Android Studio will display the sources of MParticle.java

*Actual Result*:
Android Studio will show the decompilation of the bytecode of the underlying classes.jar within the aar

4. Re-open [app/build.gradle](https://github.com/samdozor/sample-app-for-tools-team/blob/master/app/build.gradle), change `compile 'com.mparticle:android-core-test:4.2.1@aar'` to `compile 'com.mparticle:android-core-test:4.2.1@jar'`
5. Clean your project and re-sync Gradle
6. Open [MainActivity.java](https://github.com/samdozor/sample-app-for-tools-team/blob/master/app/src/main/java/com/dozor/sample/bug/MainActivity.java) and Command-Click into the `MParticle` class within `onCreate`. 
7. Android Studio will correctly display the sources of MParticle.java


### More Info

This issue is not present for the support library. The Android Studio codebase is tightly-coupled to the `com.android.support` group id - it looks specifically for it and attached sources to aar's of that group.

Here is the commit that enabled source-attachment for the Support Library: https://android.googlesource.com/platform/tools/adt/idea/+/28ccb2410ddda1a4271e177c1d6ed632f95583bd

Here is the method that could potentially be made generic for all AARs: https://android.googlesource.com/platform/tools/adt/idea/+/master/android/src/com/android/tools/idea/gradle/project/PostProjectSetupTasksExecutor.java#379


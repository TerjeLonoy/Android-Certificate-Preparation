# Android certificate preparation

* [1. Fundamental Application Components][1]
  * [1a. Describe an application's key functional and nonfunctional requirements][1a]
  * [1b. Create an Activity that displays a layout resource][1b]
  * [1c. Fetch local data from disk using a Loader on a background thread][1c]
  * [1d. Propagate data changes through a Loader to the UI][1d]
  * [1e. Schedule a time-sensitive task using alarms][1e]

* [2. Application User Interface (UI) and User Experience (UX)][2]

* [3. Persistent Data Storage][3]

* [4. Testing and Debugging][4]

## 1. Fundamental Application Components
Understanding of Android's top-level application components (Activity, Service, Broadcast Receiver, Content Provider) and the lifecycle associated with each one. Candidates should be able to describe the types of application logic that would be best suited for each component, and whether that component is executing in the foreground or in the background. This includes strategies for determining how and when to execute background work.

### 1a. Describe an application's key functional and nonfunctional requirements
A functional requirement describes what a software system should do, while non-functional requirements place constraints on how the system will do so.

**Functional requirement example**: When a user registers, the system should send an email confirming their registration

**Non-functional requirement example**: Registration confirmation email should be sent within 30 seconds of registration

### 1b. Create an Activity that displays a layout resource
Activites are interactive windows that can either be displayed fullscreen, floating or integrated into other activities.

***The bare minimum of creating an Activity and displaying a layout:***

```java
package com.somesite.somecompany.appname.activities;

import android.app.Activity;
import android.os.Bundle;
import com.somesite.somecompany.appname.R;

public class BasicActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
    }
}
```

Read more about Activities on [Androids Activity Documentation][1ba] and about their lifecycle at [Android Activity Lifecycles Documentation][1bb]

### 1c. Fetch local data from disk using a Loader on a background thread
Loaders lets you load data from either Content Providers or other data sources. They are available to every Activity and Fragment, and conforms to their respective lifecycles such as onDestroy(). Loaders also observe datasources, so that they can update the UI when the underlying datasource changes. Another important thing about Loaders is that they can cache the data they fetch, making multiple visits to an Activity or Fragment more smooth as the user do not have to wait for another data fetch.

This project contains a barebones implementation of a Loader. [LoaderExampleActivity.java][1ca] loads [activity_loader_example.xml][1cb] which only contains a Fragment. This Fragment space loads [ContactLoader.java][1cc] that loads Contacts from the phone with a Loader. Once the Loader is complete it puts the returned information into [listitem_contacts][1cd] that are items displayed in [listview_contacts][1ce]. Resulting in this:

![loaders preview](images/loaders.png)

Read more about Loaders on [Androids Loaders Documentation][1cf]

### 1d. Propagate data changes through a Loader to the UI
Propagating data with a Loader is explained and illustrated under [section 1c.][1c]

### 1e. Schedule a time-sensitive task using alarms
Alarms are used to send a Broadcast at a certain point in time. The Broadcast is picked up by a Receiver, that will perform a given task, such as sending a Notification to the device.

This project contains an implementation of an Alarm that sends a Notification at a point in time. [AlarmScheduleManager.java][1ea] starts an Alarm that triggers a Broadcast after 15 seconds. [AlarmReceiver.java][1eb] receives this Broadcast and sends a Notification to the user. When the Notification is tapped, [AlarmWakeActivity.java][1ec] is started.

## 2. Application User Interface (UI) and User Experience (UX)

## 3. Persistent Data Storage

## 4. Testing and Debugging

[1]: #1-fundamental-application-components
[1a]: #1a-describe-an-applications-key-functional-and-nonfunctional-requirement
[1b]: #1b-create-an-activity-that-displays-a-layout-resource
[1ba]: https://developer.android.com/reference/android/app/Activity.html
[1bb]: https://developer.android.com/guide/components/activities/activity-lifecycle.html
[1c]: #1c-fetch-local-data-from-disk-using-a-loader-on-a-background-thread
[1ca]: ./AndroidCertificationPreparation/app/src/main/java/com/acp/terjelonoy/androidcertificationpreparation/activities/LoaderExampleActivity.java
[1cb]: ./AndroidCertificationPreparation/app/src/main/res/layout/activity_loader_example.xml
[1cc]: ./AndroidCertificationPreparation/app/src/main/java/com/acp/terjelonoy/androidcertificationpreparation/loaders/ContactLoader.java
[1cd]: ./AndroidCertificationPreparation/app/src/main/res/layout/listitem_contacts.xml
[1ce]: ./AndroidCertificationPreparation/app/src/main/res/layout/listview_contacts.xml
[1cf]: https://developer.android.com/guide/components/loaders.html
[1d]: #1d-propagate-data-changes-through-a-loader-to-the-ui
[1e]: #1e-schedule-a-time-sensitive-task-using-alarms
[1ea]: ./AndroidCertificationPreparation/app/src/main/java/com/acp/terjelonoy/androidcertificationpreparation/managers/AlarmScheduleManager.java
[1eb]: ./AndroidCertificationPreparation/app/src/main/java/com/acp/terjelonoy/androidcertificationpreparation/receivers/AlarmReceiver.java
[1ec]: ./AndroidCertificationPreparation/app/src/main/java/com/acp/terjelonoy/androidcertificationpreparation/activities/AlarmWakeActivity.java
[2]: #2-application-user-interface-ui-and-user-experience-ux
[3]: #3-persistant-data-storage
[4]: #4-testing-and-debugging

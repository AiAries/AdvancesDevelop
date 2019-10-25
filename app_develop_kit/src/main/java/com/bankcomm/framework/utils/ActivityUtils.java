/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bankcomm.framework.utils;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * This provides methods to help Activities load their UI.
 */
public class ActivityUtils {

    public static final String TAG = ActivityUtils.class.getCanonicalName();

    /**
     * The {@code fragment} is added to the container view with id {@code frameId}. The operation is
     * performed by the {@code fragmentManager}.
     *
     */
    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                             @NonNull Fragment fragment, int frameId) {

//        checkNotNull(fragmentManager);
//        checkNotNull(fragment);
        hideFragments(fragmentManager);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }
    private static void addFragmentToActivity(@NonNull FragmentManager fragmentManager,
                                              @NonNull Fragment fragment, String tag, int frameId) {

//        checkNotNull(fragmentManager);
//        checkNotNull(fragment);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId,fragment,tag);
        transaction.commit();
    }
    public static <T> T showFragmentToActivity(@NonNull FragmentManager fragmentManager, int frameId, Class<T> tClass) {
        return showFragmentToActivity(fragmentManager, frameId, tClass, null);
    }
    public static <T> T showFragmentToActivity(@NonNull FragmentManager fragmentManager, int frameId, Class<T> tClass, Bundle bundle) {

        try {
            hideFragments(fragmentManager);
            String name = tClass.getName();
            System.out.println("fragment name "+name);
            Fragment fragment1 = fragmentManager.findFragmentByTag(name);
            if (fragment1 == null) {
                Class<?> aClass = Class.forName(name);
                fragment1 = (Fragment) aClass.newInstance();
                if (bundle!=null) {

                    fragment1.setArguments(bundle);
                }
                ActivityUtils.addFragmentToActivity(fragmentManager, fragment1, name,frameId);
            } else {
                fragmentManager.beginTransaction().show(fragment1).commit();
            }
            return (T) fragment1;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e.getMessage());
        } catch (InstantiationException e) {
            throw new RuntimeException(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage()+tClass.getName() + "必须是Fragment的全类名");
        }

    }
    private static void hideFragments(@NonNull FragmentManager fragmentManager){
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments!=null&&fragments.size()>0) {
            for (Fragment fragment : fragments) {
                if (fragment!=null&&fragment.isVisible()) {
                    fragmentManager.beginTransaction().hide(fragment).commit();
                }
            }
        }
    }


}

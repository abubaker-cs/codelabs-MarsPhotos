/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.marsphotos.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.marsphotos.network.MarsApi
import com.example.android.marsphotos.network.MarsPhoto
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData that stores the status of the most recent request
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the request status
    val status: LiveData<String> = _status

    /*
    * For Images
    * */
    private val _photos = MutableLiveData<MarsPhoto>()

    // Public backing field called photos of the type, LiveData<MarsPhoto>
    val photos: LiveData<MarsPhoto> = _photos

    /**
     * Call getMarsPhotos() on init so we can display status immediately.
     */
    init {

        // updates the placeholder response.
        getMarsPhotos()

    }

    /**
     * TODO: Gets Mars photos information from the Mars API Retrofit service and updates the
     * [MarsPhoto] [List] [LiveData].
     */
    private fun getMarsPhotos() {

        // Updating the value of _status property, updates the placeholder text displayed on the screen.
        // _status.value = "Set the Mars API status response here!"

        viewModelScope.launch {

            try {
                _photos.value = MarsApi.retrofitService.getPhotos()[0]
                _status.value = "First Mars image URL: ${_photos.value!!.imgSrcUrl}"

                // val listResult = MarsApi.retrofitService.getPhotos()
                // _status.value = "Success: ${listResult.size} Mars photos retrieved"
            } catch (e: Exception) {
                _status.value = "Failure: ${e.message}"
            }

        }

    }
}

// TODO: The goal for this codelab is to update the status LiveData within the ViewModel using
//       real data you get from the internet.

/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.ivansv.nskweather.presentation.exception;

import android.content.Context;

import com.example.ivansv.nskweather.R;
import com.example.ivansv.nskweather.data.exception.ForecastNotFoundException;
import com.example.ivansv.nskweather.data.exception.NetworkConnectionException;


public class ErrorMessageFactory {

    private ErrorMessageFactory() {
    }

    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);
        if (exception instanceof NetworkConnectionException || exception.getMessage().equals(context.getString(R.string.error_message))) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof ForecastNotFoundException) {
            message = context.getString(R.string.exception_message_forecast_not_found);
        }
        return message;
    }
}

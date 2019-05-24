package com.visualisation.currency_exchange;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class JsonParserForVisualisation {

    // TODO: testy - przyda się do UnitTest?
    public static void main(String[] args) throws IOException {
        String json = "{\n" +
                "    \"Meta Data\": {\n" +
                "        \"1. Information\": \"FX Intraday (60min) Time Series\",\n" +
                "        \"2. From Symbol\": \"USD\",\n" +
                "        \"3. To Symbol\": \"PLN\",\n" +
                "        \"4. Last Refreshed\": \"2019-05-22 14:00:00\",\n" +
                "        \"5. Interval\": \"60min\",\n" +
                "        \"6. Output Size\": \"Compact\",\n" +
                "        \"7. Time Zone\": \"UTC\"\n" +
                "    },\n" +
                "    \"Time Series FX (60min)\": {\n" +
                "        \"2019-05-22 14:00:00\": {\n" +
                "            \"1. open\": \"3.8567\",\n" +
                "            \"2. high\": \"3.8574\",\n" +
                "            \"3. low\": \"3.8515\",\n" +
                "            \"4. close\": \"3.8571\"\n" +
                "        },\n" +
                "        \"2019-05-22 13:00:00\": {\n" +
                "            \"1. open\": \"3.8538\",\n" +
                "            \"2. high\": \"3.8589\",\n" +
                "            \"3. low\": \"3.8510\",\n" +
                "            \"4. close\": \"3.8567\"\n" +
                "        },\n" +
                "        \"2019-05-22 12:00:00\": {\n" +
                "            \"1. open\": \"3.8539\",\n" +
                "            \"2. high\": \"3.8544\",\n" +
                "            \"3. low\": \"3.8471\",\n" +
                "            \"4. close\": \"3.8538\"\n" +
                "        },\n" +
                "        \"2019-05-22 11:00:00\": {\n" +
                "            \"1. open\": \"3.8541\",\n" +
                "            \"2. high\": \"3.8577\",\n" +
                "            \"3. low\": \"3.8505\",\n" +
                "            \"4. close\": \"3.8539\"\n" +
                "        },\n" +
                "        \"2019-05-22 10:00:00\": {\n" +
                "            \"1. open\": \"3.8569\",\n" +
                "            \"2. high\": \"3.8578\",\n" +
                "            \"3. low\": \"3.8507\",\n" +
                "            \"4. close\": \"3.8541\"\n" +
                "        },\n" +
                "        \"2019-05-22 09:00:00\": {\n" +
                "            \"1. open\": \"3.8616\",\n" +
                "            \"2. high\": \"3.8622\",\n" +
                "            \"3. low\": \"3.8538\",\n" +
                "            \"4. close\": \"3.8570\"\n" +
                "        },\n" +
                "        \"2019-05-22 08:00:00\": {\n" +
                "            \"1. open\": \"3.8651\",\n" +
                "            \"2. high\": \"3.8662\",\n" +
                "            \"3. low\": \"3.8565\",\n" +
                "            \"4. close\": \"3.8616\"\n" +
                "        },\n" +
                "        \"2019-05-22 07:00:00\": {\n" +
                "            \"1. open\": \"3.8616\",\n" +
                "            \"2. high\": \"3.8666\",\n" +
                "            \"3. low\": \"3.8597\",\n" +
                "            \"4. close\": \"3.8651\"\n" +
                "        },\n" +
                "        \"2019-05-22 06:00:00\": {\n" +
                "            \"1. open\": \"3.8600\",\n" +
                "            \"2. high\": \"3.8629\",\n" +
                "            \"3. low\": \"3.8577\",\n" +
                "            \"4. close\": \"3.8616\"\n" +
                "        },\n" +
                "        \"2019-05-22 05:00:00\": {\n" +
                "            \"1. open\": \"3.8590\",\n" +
                "            \"2. high\": \"3.8607\",\n" +
                "            \"3. low\": \"3.8571\",\n" +
                "            \"4. close\": \"3.8593\"\n" +
                "        },\n" +
                "        \"2019-05-22 04:00:00\": {\n" +
                "            \"1. open\": \"3.8561\",\n" +
                "            \"2. high\": \"3.8605\",\n" +
                "            \"3. low\": \"3.8553\",\n" +
                "            \"4. close\": \"3.8590\"\n" +
                "        },\n" +
                "        \"2019-05-22 03:00:00\": {\n" +
                "            \"1. open\": \"3.8573\",\n" +
                "            \"2. high\": \"3.8579\",\n" +
                "            \"3. low\": \"3.8549\",\n" +
                "            \"4. close\": \"3.8569\"\n" +
                "        },\n" +
                "        \"2019-05-22 02:00:00\": {\n" +
                "            \"1. open\": \"3.8559\",\n" +
                "            \"2. high\": \"3.8584\",\n" +
                "            \"3. low\": \"3.8540\",\n" +
                "            \"4. close\": \"3.8573\"\n" +
                "        },\n" +
                "        \"2019-05-22 01:00:00\": {\n" +
                "            \"1. open\": \"3.8571\",\n" +
                "            \"2. high\": \"3.8579\",\n" +
                "            \"3. low\": \"3.8539\",\n" +
                "            \"4. close\": \"3.8559\"\n" +
                "        },\n" +
                "        \"2019-05-22 00:00:00\": {\n" +
                "            \"1. open\": \"3.8559\",\n" +
                "            \"2. high\": \"3.8580\",\n" +
                "            \"3. low\": \"3.8539\",\n" +
                "            \"4. close\": \"3.8571\"\n" +
                "        },\n" +
                "        \"2019-05-21 23:00:00\": {\n" +
                "            \"1. open\": \"3.8569\",\n" +
                "            \"2. high\": \"3.8577\",\n" +
                "            \"3. low\": \"3.8532\",\n" +
                "            \"4. close\": \"3.8559\"\n" +
                "        },\n" +
                "        \"2019-05-21 22:00:00\": {\n" +
                "            \"1. open\": \"3.8557\",\n" +
                "            \"2. high\": \"3.8577\",\n" +
                "            \"3. low\": \"3.8544\",\n" +
                "            \"4. close\": \"3.8565\"\n" +
                "        },\n" +
                "        \"2019-05-21 21:00:00\": {\n" +
                "            \"1. open\": \"3.8580\",\n" +
                "            \"2. high\": \"3.8591\",\n" +
                "            \"3. low\": \"3.8540\",\n" +
                "            \"4. close\": \"3.8562\"\n" +
                "        },\n" +
                "        \"2019-05-21 20:00:00\": {\n" +
                "            \"1. open\": \"3.8569\",\n" +
                "            \"2. high\": \"3.8580\",\n" +
                "            \"3. low\": \"3.8539\",\n" +
                "            \"4. close\": \"3.8580\"\n" +
                "        },\n" +
                "        \"2019-05-21 19:00:00\": {\n" +
                "            \"1. open\": \"3.8595\",\n" +
                "            \"2. high\": \"3.8598\",\n" +
                "            \"3. low\": \"3.8551\",\n" +
                "            \"4. close\": \"3.8570\"\n" +
                "        },\n" +
                "        \"2019-05-21 18:00:00\": {\n" +
                "            \"1. open\": \"3.8586\",\n" +
                "            \"2. high\": \"3.8608\",\n" +
                "            \"3. low\": \"3.8567\",\n" +
                "            \"4. close\": \"3.8586\"\n" +
                "        },\n" +
                "        \"2019-05-21 17:00:00\": {\n" +
                "            \"1. open\": \"3.8588\",\n" +
                "            \"2. high\": \"3.8593\",\n" +
                "            \"3. low\": \"3.8559\",\n" +
                "            \"4. close\": \"3.8586\"\n" +
                "        },\n" +
                "        \"2019-05-21 16:00:00\": {\n" +
                "            \"1. open\": \"3.8603\",\n" +
                "            \"2. high\": \"3.8606\",\n" +
                "            \"3. low\": \"3.8566\",\n" +
                "            \"4. close\": \"3.8582\"\n" +
                "        },\n" +
                "        \"2019-05-21 15:00:00\": {\n" +
                "            \"1. open\": \"3.8515\",\n" +
                "            \"2. high\": \"3.8610\",\n" +
                "            \"3. low\": \"3.8482\",\n" +
                "            \"4. close\": \"3.8602\"\n" +
                "        },\n" +
                "        \"2019-05-21 14:00:00\": {\n" +
                "            \"1. open\": \"3.8622\",\n" +
                "            \"2. high\": \"3.8634\",\n" +
                "            \"3. low\": \"3.8474\",\n" +
                "            \"4. close\": \"3.8515\"\n" +
                "        },\n" +
                "        \"2019-05-21 13:00:00\": {\n" +
                "            \"1. open\": \"3.8592\",\n" +
                "            \"2. high\": \"3.8628\",\n" +
                "            \"3. low\": \"3.8562\",\n" +
                "            \"4. close\": \"3.8622\"\n" +
                "        },\n" +
                "        \"2019-05-21 12:00:00\": {\n" +
                "            \"1. open\": \"3.8646\",\n" +
                "            \"2. high\": \"3.8647\",\n" +
                "            \"3. low\": \"3.8559\",\n" +
                "            \"4. close\": \"3.8592\"\n" +
                "        },\n" +
                "        \"2019-05-21 11:00:00\": {\n" +
                "            \"1. open\": \"3.8637\",\n" +
                "            \"2. high\": \"3.8669\",\n" +
                "            \"3. low\": \"3.8615\",\n" +
                "            \"4. close\": \"3.8639\"\n" +
                "        },\n" +
                "        \"2019-05-21 10:00:00\": {\n" +
                "            \"1. open\": \"3.8633\",\n" +
                "            \"2. high\": \"3.8648\",\n" +
                "            \"3. low\": \"3.8582\",\n" +
                "            \"4. close\": \"3.8637\"\n" +
                "        },\n" +
                "        \"2019-05-21 09:00:00\": {\n" +
                "            \"1. open\": \"3.8622\",\n" +
                "            \"2. high\": \"3.8646\",\n" +
                "            \"3. low\": \"3.8556\",\n" +
                "            \"4. close\": \"3.8633\"\n" +
                "        },\n" +
                "        \"2019-05-21 08:00:00\": {\n" +
                "            \"1. open\": \"3.8552\",\n" +
                "            \"2. high\": \"3.8645\",\n" +
                "            \"3. low\": \"3.8532\",\n" +
                "            \"4. close\": \"3.8621\"\n" +
                "        },\n" +
                "        \"2019-05-21 07:00:00\": {\n" +
                "            \"1. open\": \"3.8550\",\n" +
                "            \"2. high\": \"3.8599\",\n" +
                "            \"3. low\": \"3.8523\",\n" +
                "            \"4. close\": \"3.8552\"\n" +
                "        },\n" +
                "        \"2019-05-21 06:00:00\": {\n" +
                "            \"1. open\": \"3.8517\",\n" +
                "            \"2. high\": \"3.8581\",\n" +
                "            \"3. low\": \"3.8497\",\n" +
                "            \"4. close\": \"3.8556\"\n" +
                "        },\n" +
                "        \"2019-05-21 05:00:00\": {\n" +
                "            \"1. open\": \"3.8491\",\n" +
                "            \"2. high\": \"3.8522\",\n" +
                "            \"3. low\": \"3.8471\",\n" +
                "            \"4. close\": \"3.8514\"\n" +
                "        },\n" +
                "        \"2019-05-21 04:00:00\": {\n" +
                "            \"1. open\": \"3.8486\",\n" +
                "            \"2. high\": \"3.8496\",\n" +
                "            \"3. low\": \"3.8464\",\n" +
                "            \"4. close\": \"3.8491\"\n" +
                "        },\n" +
                "        \"2019-05-21 03:00:00\": {\n" +
                "            \"1. open\": \"3.8477\",\n" +
                "            \"2. high\": \"3.8490\",\n" +
                "            \"3. low\": \"3.8453\",\n" +
                "            \"4. close\": \"3.8486\"\n" +
                "        },\n" +
                "        \"2019-05-21 02:00:00\": {\n" +
                "            \"1. open\": \"3.8482\",\n" +
                "            \"2. high\": \"3.8514\",\n" +
                "            \"3. low\": \"3.8459\",\n" +
                "            \"4. close\": \"3.8477\"\n" +
                "        },\n" +
                "        \"2019-05-21 01:00:00\": {\n" +
                "            \"1. open\": \"3.8483\",\n" +
                "            \"2. high\": \"3.8519\",\n" +
                "            \"3. low\": \"3.8459\",\n" +
                "            \"4. close\": \"3.8506\"\n" +
                "        },\n" +
                "        \"2019-05-21 00:00:00\": {\n" +
                "            \"1. open\": \"3.8475\",\n" +
                "            \"2. high\": \"3.8491\",\n" +
                "            \"3. low\": \"3.8456\",\n" +
                "            \"4. close\": \"3.8483\"\n" +
                "        },\n" +
                "        \"2019-05-20 23:00:00\": {\n" +
                "            \"1. open\": \"3.8458\",\n" +
                "            \"2. high\": \"3.8479\",\n" +
                "            \"3. low\": \"3.8440\",\n" +
                "            \"4. close\": \"3.8475\"\n" +
                "        },\n" +
                "        \"2019-05-20 22:00:00\": {\n" +
                "            \"1. open\": \"3.8478\",\n" +
                "            \"2. high\": \"3.8487\",\n" +
                "            \"3. low\": \"3.8448\",\n" +
                "            \"4. close\": \"3.8466\"\n" +
                "        },\n" +
                "        \"2019-05-20 21:00:00\": {\n" +
                "            \"1. open\": \"3.8478\",\n" +
                "            \"2. high\": \"3.8512\",\n" +
                "            \"3. low\": \"3.8437\",\n" +
                "            \"4. close\": \"3.8479\"\n" +
                "        },\n" +
                "        \"2019-05-20 20:00:00\": {\n" +
                "            \"1. open\": \"3.8504\",\n" +
                "            \"2. high\": \"3.8511\",\n" +
                "            \"3. low\": \"3.8460\",\n" +
                "            \"4. close\": \"3.8485\"\n" +
                "        },\n" +
                "        \"2019-05-20 19:00:00\": {\n" +
                "            \"1. open\": \"3.8491\",\n" +
                "            \"2. high\": \"3.8511\",\n" +
                "            \"3. low\": \"3.8470\",\n" +
                "            \"4. close\": \"3.8498\"\n" +
                "        },\n" +
                "        \"2019-05-20 18:00:00\": {\n" +
                "            \"1. open\": \"3.8493\",\n" +
                "            \"2. high\": \"3.8501\",\n" +
                "            \"3. low\": \"3.8467\",\n" +
                "            \"4. close\": \"3.8491\"\n" +
                "        },\n" +
                "        \"2019-05-20 17:00:00\": {\n" +
                "            \"1. open\": \"3.8491\",\n" +
                "            \"2. high\": \"3.8499\",\n" +
                "            \"3. low\": \"3.8463\",\n" +
                "            \"4. close\": \"3.8488\"\n" +
                "        },\n" +
                "        \"2019-05-20 16:00:00\": {\n" +
                "            \"1. open\": \"3.8484\",\n" +
                "            \"2. high\": \"3.8505\",\n" +
                "            \"3. low\": \"3.8462\",\n" +
                "            \"4. close\": \"3.8491\"\n" +
                "        },\n" +
                "        \"2019-05-20 15:00:00\": {\n" +
                "            \"1. open\": \"3.8482\",\n" +
                "            \"2. high\": \"3.8488\",\n" +
                "            \"3. low\": \"3.8437\",\n" +
                "            \"4. close\": \"3.8483\"\n" +
                "        },\n" +
                "        \"2019-05-20 14:00:00\": {\n" +
                "            \"1. open\": \"3.8491\",\n" +
                "            \"2. high\": \"3.8502\",\n" +
                "            \"3. low\": \"3.8451\",\n" +
                "            \"4. close\": \"3.8482\"\n" +
                "        },\n" +
                "        \"2019-05-20 13:00:00\": {\n" +
                "            \"1. open\": \"3.8488\",\n" +
                "            \"2. high\": \"3.8515\",\n" +
                "            \"3. low\": \"3.8456\",\n" +
                "            \"4. close\": \"3.8491\"\n" +
                "        },\n" +
                "        \"2019-05-20 12:00:00\": {\n" +
                "            \"1. open\": \"3.8502\",\n" +
                "            \"2. high\": \"3.8506\",\n" +
                "            \"3. low\": \"3.8455\",\n" +
                "            \"4. close\": \"3.8488\"\n" +
                "        },\n" +
                "        \"2019-05-20 11:00:00\": {\n" +
                "            \"1. open\": \"3.8539\",\n" +
                "            \"2. high\": \"3.8543\",\n" +
                "            \"3. low\": \"3.8473\",\n" +
                "            \"4. close\": \"3.8502\"\n" +
                "        },\n" +
                "        \"2019-05-20 10:00:00\": {\n" +
                "            \"1. open\": \"3.8540\",\n" +
                "            \"2. high\": \"3.8557\",\n" +
                "            \"3. low\": \"3.8508\",\n" +
                "            \"4. close\": \"3.8539\"\n" +
                "        },\n" +
                "        \"2019-05-20 09:00:00\": {\n" +
                "            \"1. open\": \"3.8543\",\n" +
                "            \"2. high\": \"3.8548\",\n" +
                "            \"3. low\": \"3.8493\",\n" +
                "            \"4. close\": \"3.8540\"\n" +
                "        },\n" +
                "        \"2019-05-20 08:00:00\": {\n" +
                "            \"1. open\": \"3.8518\",\n" +
                "            \"2. high\": \"3.8548\",\n" +
                "            \"3. low\": \"3.8464\",\n" +
                "            \"4. close\": \"3.8543\"\n" +
                "        },\n" +
                "        \"2019-05-20 07:00:00\": {\n" +
                "            \"1. open\": \"3.8561\",\n" +
                "            \"2. high\": \"3.8565\",\n" +
                "            \"3. low\": \"3.8491\",\n" +
                "            \"4. close\": \"3.8518\"\n" +
                "        },\n" +
                "        \"2019-05-20 06:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8598\",\n" +
                "            \"3. low\": \"3.8530\",\n" +
                "            \"4. close\": \"3.8561\"\n" +
                "        },\n" +
                "        \"2019-05-20 05:00:00\": {\n" +
                "            \"1. open\": \"3.8578\",\n" +
                "            \"2. high\": \"3.8596\",\n" +
                "            \"3. low\": \"3.8560\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-20 04:00:00\": {\n" +
                "            \"1. open\": \"3.8564\",\n" +
                "            \"2. high\": \"3.8590\",\n" +
                "            \"3. low\": \"3.8543\",\n" +
                "            \"4. close\": \"3.8578\"\n" +
                "        },\n" +
                "        \"2019-05-20 03:00:00\": {\n" +
                "            \"1. open\": \"3.8547\",\n" +
                "            \"2. high\": \"3.8572\",\n" +
                "            \"3. low\": \"3.8535\",\n" +
                "            \"4. close\": \"3.8564\"\n" +
                "        },\n" +
                "        \"2019-05-20 02:00:00\": {\n" +
                "            \"1. open\": \"3.8541\",\n" +
                "            \"2. high\": \"3.8565\",\n" +
                "            \"3. low\": \"3.8522\",\n" +
                "            \"4. close\": \"3.8548\"\n" +
                "        },\n" +
                "        \"2019-05-20 01:00:00\": {\n" +
                "            \"1. open\": \"3.8532\",\n" +
                "            \"2. high\": \"3.8555\",\n" +
                "            \"3. low\": \"3.8505\",\n" +
                "            \"4. close\": \"3.8539\"\n" +
                "        },\n" +
                "        \"2019-05-20 00:00:00\": {\n" +
                "            \"1. open\": \"3.8557\",\n" +
                "            \"2. high\": \"3.8590\",\n" +
                "            \"3. low\": \"3.8516\",\n" +
                "            \"4. close\": \"3.8532\"\n" +
                "        },\n" +
                "        \"2019-05-19 23:00:00\": {\n" +
                "            \"1. open\": \"3.8558\",\n" +
                "            \"2. high\": \"3.8582\",\n" +
                "            \"3. low\": \"3.8534\",\n" +
                "            \"4. close\": \"3.8557\"\n" +
                "        },\n" +
                "        \"2019-05-19 22:00:00\": {\n" +
                "            \"1. open\": \"3.8552\",\n" +
                "            \"2. high\": \"3.8599\",\n" +
                "            \"3. low\": \"3.8520\",\n" +
                "            \"4. close\": \"3.8563\"\n" +
                "        },\n" +
                "        \"2019-05-19 21:00:00\": {\n" +
                "            \"1. open\": \"3.8567\",\n" +
                "            \"2. high\": \"3.8598\",\n" +
                "            \"3. low\": \"3.8506\",\n" +
                "            \"4. close\": \"3.8553\"\n" +
                "        },\n" +
                "        \"2019-05-19 20:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8593\",\n" +
                "            \"3. low\": \"3.8547\",\n" +
                "            \"4. close\": \"3.8571\"\n" +
                "        },\n" +
                "        \"2019-05-19 19:00:00\": {\n" +
                "            \"1. open\": \"3.8536\",\n" +
                "            \"2. high\": \"3.8601\",\n" +
                "            \"3. low\": \"3.8536\",\n" +
                "            \"4. close\": \"3.8556\"\n" +
                "        },\n" +
                "        \"2019-05-19 18:00:00\": {\n" +
                "            \"1. open\": \"3.8567\",\n" +
                "            \"2. high\": \"3.8567\",\n" +
                "            \"3. low\": \"3.8567\",\n" +
                "            \"4. close\": \"3.8567\"\n" +
                "        },\n" +
                "        \"2019-05-19 17:00:00\": {\n" +
                "            \"1. open\": \"3.8568\",\n" +
                "            \"2. high\": \"3.8568\",\n" +
                "            \"3. low\": \"3.8567\",\n" +
                "            \"4. close\": \"3.8567\"\n" +
                "        },\n" +
                "        \"2019-05-19 16:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 15:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 14:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 13:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 12:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 11:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 10:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 09:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 08:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 07:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 06:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 05:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 04:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 03:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 02:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 01:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-19 00:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 23:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 22:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 21:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 20:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 19:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 18:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 17:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 16:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 15:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 14:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 13:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 12:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        },\n" +
                "        \"2019-05-18 11:00:00\": {\n" +
                "            \"1. open\": \"3.8585\",\n" +
                "            \"2. high\": \"3.8585\",\n" +
                "            \"3. low\": \"3.8585\",\n" +
                "            \"4. close\": \"3.8585\"\n" +
                "        }\n" +
                "    }\n" +
                "}";

        JsonParserForVisualisation parser = new JsonParserForVisualisation();
        String jsonString = parser.getJsonStringWithoutRoot(json);
        ObjectMapper mapper = new ObjectMapper();
        Map<String, LinkedHashMap<String, String>> map = mapper.readValue(jsonString, new TypeReference<Map<String, Object>>() {
        });
        System.out.println(map.entrySet());
        List<VisualisationObject> objects = parser.getVisualisationObjectFromJsonString(jsonString, mapper);
        System.out.println(objects.get(1).getHigh());
        System.out.println(objects.get(1).getLow());
        System.out.println(objects.get(0).getDate());
        System.out.println(objects.toString());

    }

    public JsonParserForVisualisation() {
    }

    public String getJsonStringWithoutRoot(String json) {
        int i = json.indexOf(")\":");
        return json.substring(i + 4, json.length() - 2);
    }

    public List<VisualisationObject> getVisualisationObjectFromJsonString(String json, ObjectMapper mapper)
            throws IOException {
        String jsonString = getJsonStringWithoutRoot(json);
        Map<String, LinkedHashMap<String, String>> map = mapper.readValue(
                jsonString, new TypeReference<Map<String, Object>>() {
                });
        List<VisualisationObject> list = new ArrayList<>();
        map.forEach((key, value) -> {
            VisualisationObject v = new VisualisationObject();
            try {
                v.setDate(dateFormatter(key));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            v.setOpen(Double.parseDouble(value.get("1. open")));
            v.setHigh(Double.parseDouble(value.get("2. high")));
            v.setLow(Double.parseDouble(value.get("3. low")));
            v.setClose(Double.parseDouble(value.get("4. close")));
            list.add(v);
        });
        return list;
    }

    public Date dateFormatter(String date) throws ParseException {
        SimpleDateFormat formatter;
        if (date.contains(" ")) {
            formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            formatter = new SimpleDateFormat("yyyy-MM-dd");
        }
        return formatter.parse(date);
    }
}

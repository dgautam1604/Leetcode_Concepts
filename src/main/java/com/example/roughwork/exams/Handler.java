//import java.io.*;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.TimeZone;
//
//import com.amazonaws.services.dynamodbv2.document.*;
//import com.amazonaws.services.dynamodbv2.document.spec.QuerySpec;
//import com.amazonaws.services.lambda.runtime.Context;
//import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//
//public class Handler implements RequestStreamHandler {
//
//    private String getDateNow() {
//        // Use the following date format for "now":
//        TimeZone tz = TimeZone.getTimeZone("UTC");
//        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
//        df.setTimeZone(tz);
//        return df.format(new Date());
//    }
//
//    private List<JSONObject> queryUserNotes(String userEmail, int limit) {
//        List<JSONObject> resultItems = new ArrayList<>();
//        String tableName = "user-notes";
//
//        try {
//            Table table = DynamoDBClient.client.getTable(tableName);
//
//            // Query the DynamoDB database for user notes with a limit
//            QuerySpec querySpec = new QuerySpec()
//                    .withKeyConditionExpression("user = :v_user")
//                    .withValueMap(new ValueMap()
//                            .withString(":v_user", userEmail))
//                    .withScanIndexForward(false) // Sort in descending order by create_date
//                    .withMaxResultSize(limit);
//
//            ItemCollection<QueryOutcome> items = table.query(querySpec);
//
//            for (Item item : items) {
//                resultItems.add(new JSONObject(item.asMap()));
//            }
//
//            return resultItems;
//        } catch (Exception exc) {
//            // Handle exceptions (log or throw as needed)
//            exc.printStackTrace();
//            return null;
//        }
//    }
//
//    public static String getAuthenticatedUserEmail(String token) {
//        String tableName = "token-email-lookup";
//
//        try {
//            Table table = DynamoDBClient.client.getTable(tableName);
//            Item item = table.getItem("token", token);
//
//            if (item != null) {
//                return item.getString("email");
//            }
//            return null;
//        } catch (Exception exc) {
//            // Handle exceptions (log or throw as needed)
//            exc.printStackTrace();
//            return null;
//        }
//    }
//
//    private String authenticateUser(JSONObject headers) {
//        String authenticationHeader = (String) headers.get("Authentication");
//
//        // Validate the Authentication header and retrieve token
//        if (authenticationHeader != null && authenticationHeader.startsWith("Bearer ")) {
//            String token = authenticationHeader.substring(7); // Extract the token from the header
//
//            if (!token.isEmpty()) {
//                return getAuthenticatedUserEmail(token);
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context) throws IOException {
//        JSONParser parser = new JSONParser();
//        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//        JSONObject responseJson = new JSONObject();
//        OutputStreamWriter writer = new OutputStreamWriter(outputStream, "UTF-8");
//
//        try {
//            JSONObject event = (JSONObject) parser.parse(reader);
//            JSONObject headers = (JSONObject) event.get("headers");
//
//            String email = authenticateUser(headers);
//
//            if (email != null) {
//                List<JSONObject> resultItems = queryUserNotes(email, 10);
//
//                if (resultItems != null) {
//                    responseJson.put("statusCode", 200);
//                    responseJson.put("body", resultItems.toString());
//                } else {
//                    responseJson.put("statusCode", 500);
//                    responseJson.put("body", "Internal Server Error");
//                }
//            } else {
//                responseJson.put("statusCode", 403);
//                responseJson.put("body", "Unauthorized");
//            }
//        } catch (Exception exc) {
//            // Handle exceptions (log or throw as needed)
//            exc.printStackTrace();
//            responseJson.put("statusCode", 400);
//            responseJson.put("body", "Bad Request");
//        }
//
//        writer.write(responseJson.toString());
//        writer.close();
//    }
//}

package com.example.coronavirus_stats_app.Services;

import com.example.coronavirus_stats_app.DataModels.MailingListUserDetails;
import com.example.coronavirus_stats_app.Repositories.MailingListUserRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.NumberFormat;
import java.util.*;

@Service
public class SendMailService {
    @Value("${from.email.address}")
    private String fromEmailAddress;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private HomeService homeService;

    @Autowired
    private NewsService newsService;

    @Autowired
    private MailingListUserRepository mailingListUserRepository;

    // This is the method to send the email through Gmail SMTP
    @Async
    @Scheduled(cron = "0 30 12 ? * *", zone = "UTC")
    public void sendEmail() throws MessagingException, JSONException {

        // Get the news content
        Map<Integer, List<String>> newsContent = buildNewsContent(newsService.getNewsJSONObject());

        String subject = "Daily Coronavirus Update";
        String content = htmlBuilder(newsContent);
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromEmailAddress);
        helper.setSubject(subject);
        helper.setText(content, true);

        for (MailingListUserDetails userDetails: mailingListUserRepository.findAll()) {
            helper.setTo(userDetails.getEmailAddress());
            mailSender.send(message);
        }

    }

    /*
    *
    * This is the helper method to build the responsive email html as a string.
    * The source template is from:
    *   https://webdesign.tutsplus.com/articles/creating-a-simple-responsive-html-email--webdesign-12978
    * and modified accordingly
    *
    * */
    public String htmlBuilder(Map<Integer, List<String>> newsContent) {
        StringBuilder html = new StringBuilder("<!DOCTYPE html>\n" +
                "<html lang=\"en\" xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"utf-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width,initial-scale=1\">\n" +
                "    <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "    <title></title>\n" +
                "    <style>\n" +
                "    @import url(\"https://fonts.googleapis.com/css2?family=Montserrat&display=swap\");\n" +
                "        table,\n" +
                "        td,\n" +
                "        div,\n" +
                "        h1,\n" +
                "        p {\n" +
                "            font-family: Montserrat, sans-serif;\n" +
                "        }\n" +
                "\n" +
                "        @media screen and (max-width: 530px) {\n" +
                "            .unsub {\n" +
                "                display: block;\n" +
                "                padding: 8px;\n" +
                "                margin-top: 14px;\n" +
                "                border-radius: 6px;\n" +
                "                background-color: #555555;\n" +
                "                text-decoration: none !important;\n" +
                "                font-weight: bold;\n" +
                "            }\n" +
                "\n" +
                "            .col-lge {\n" +
                "                max-width: 100% !important;\n" +
                "            }\n" +
                "        }\n" +
                "\n" +
                "        @media screen and (min-width: 531px) {\n" +
                "            .col-sml {\n" +
                "                max-width: 27% !important;\n" +
                "            }\n" +
                "\n" +
                "            .col-lge {\n" +
                "                max-width: 73% !important;\n" +
                "            }\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "\n" +
                "<body style=\"margin:0;padding:0;word-spacing:normal;background-color:#121212;\">\n" +
                "    <div role=\"article\" aria-roledescription=\"email\" lang=\"en\"\n" +
                "        style=\"text-size-adjust:100%;-webkit-text-size-adjust:100%;-ms-text-size-adjust:100%;background-color:#121212;\">\n" +
                "        <table role=\"presentation\" style=\"width:100%;border:none;border-spacing:0;\">\n" +
                "            <tr>\n" +
                "                <td align=\"center\" style=\"padding:0;\">\n" +
                "                   \n" +
                "                    <table role=\"presentation\"\n" +
                "                        style=\"width:94%;max-width:600px;border:none;border-spacing:0;text-align:left;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:#363636;\">\n" +
                "                        <tr>\n" +
                "                            <td style=\"padding:30px;padding-bottom: 0px;background-color:rgba(255, 255, 255, 0.137);\">\n" +
                "                                <h1 align=\"center\"\n" +
                "                                    style=\"margin-top:0;margin-bottom:0px;font-size:40px;line-height:48px;font-weight:bold;letter-spacing:-0.02em;color:whitesmoke\">\n" +
                "                                    Coronavirus Daily Updates\n" +
                "                                </h1>\n" +
                "\n" +
                "                                <img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/8/82/SARS-CoV-2_without_background.png/895px-SARS-CoV-2_without_background.png\"\n" +
                "                                    width=\"165\" style=\"padding-top: 16px;display: block;margin-left: auto;margin-right: auto;width:80%;max-width:165px;height:auto;border:none;text-decoration:none;color:#ffffff;\">\n" +
                "                                \n" +
                "                            </td>\n" +
                "                            <td>\n" +
                "                                \n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "                        <tr>\n" +
                "                            <td style=\"border-bottom-left-radius: 16px;border-bottom-right-radius: 16px;padding-top:0px;padding-bottom:32px;font-size:18px;background-color:rgba(255, 255, 255, 0.137);color:whitesmoke;align-items: center;\">\n" +
                "                                <h3 style=\"margin:0;padding:16px\" text-align=\"center\" align=\"center\" margin-bottom:48px>\n" +
                "                                    Confirmed Cases: ");

        html.append(NumberFormat.getInstance().format(homeService.getAllData().getGlobalConfirmedCases()));
        html.append("</h3>\n" + "<h3 style=\"margin:0;\" text-align=\"center\" align=\"center\">\n" + "                                    Confirmed Deaths: ");
        html.append(NumberFormat.getInstance().format(homeService.getAllData().getGlobalDeaths()));
        html.append("</h3>\n");
        html.append("<p style=\"font-size:16px;padding-top:16px;\" align=\"center\">\n" +
                "                                    <a href=\"http://covid-app-bucket.s3-website.us-east-2.amazonaws.com/countries/all\"\n" +
                "                                        style=\"background: #03DAC5; text-decoration: none; padding: 10px 25px; color: #ffffff; border-radius: 4px; display:inline-block; mso-padding-alt:0;\">\n" +
                "                                        <!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%;mso-text-raise:20pt\">&nbsp;</i><![endif]--><span\n" +
                "                                            style=\"mso-text-raise:10pt;font-weight:bold;\">Click for more statistics</span>\n" +
                "                                        <!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%\">&nbsp;</i><![endif]-->\n" +
                "                                    </a>\n" +
                "                                </p>\n" +
                "                            </td>\n" +
                "                        </tr>");

        html.append(" <tr>\n" +
                "                            <td style=\"padding:16px;font-size:24px;line-height:28px;font-weight:bold;\">\n" +
                "                                \n" +
                "                            </td>\n" +
                "                        </tr>\n" +
                "\n" +
                "                        <tr>\n" +
                "                            <td style=\"color: #ffffff;border-top-left-radius: 16px;border-top-right-radius: 16px;padding-top:16px;line-height:48px;padding-bottom:16px;font-size:18px;background-color:#03DAC5;align-items: center;\">\n" +
                "                                <h1 align=\"center\">Latest Coronavirus News</h1>\n" +
                "                            </td>\n" +
                "                        </tr>\n");

        for (int newsID: newsContent.keySet()) {
            List<String> content = newsContent.get(newsID);

            String newsHTML = "                       <tr>\n" +
                    "                            <td\n" +
                    "                                style=\"color:whitesmoke;padding:35px 30px 11px 30px;font-size:0;background-color:rgba(255, 255, 255, 0.137);align-items: center;border-bottom:1px solid #f0f0f5;border-color:rgba(201,201,207,.35);\">\n" +
                    "                              \n" +
                    "                                <div class=\"col-sml\"\n" +
                    "                                    style=\"padding-top: 70px;color:whitesmoke;display:inline-block;width:100%;max-width:145px;vertical-align:top;text-align:left;font-family:Arial,sans-serif;font-size:14px;color:whitesmoke;\">\n" +
                    "                                    <img src=";

            // Media link
            newsHTML += content.get(2);

            newsHTML += " width=\"115\" alt=\"\"\n" +
                    "                                        style=\"width:80%;max-width:115px;margin-bottom:20px;\">\n" +
                    "                                </div>\n" +
                    "                             \n" +
                    "                                <div class=\"col-lge\"\n" +
                    "                                    style=\"display:inline-block;width:100%;max-width:395px;vertical-align:top;padding-bottom:20px;font-family:Arial,sans-serif;font-size:16px;line-height:22px;color:whitesmoke;\">\n" +
                    "                                    <h2>";

            // Title of the news
            newsHTML += content.get(0);

            newsHTML += "</h2>\n" +
                    "                                    <p style=\"margin-top:0;margin-bottom:12px;\">";

            // Summary/Excerpt
            newsHTML += content.get(3);

            newsHTML += "</p>\n" +
                    "                                    <p style=\"margin:0;\"><a href=";

            // Link HREF
            newsHTML += content.get(1);

            newsHTML += "\n" +
                    "                                            style=\"background: #03DAC5; text-decoration: none; padding: 10px 25px; color: #ffffff; border-radius: 4px; display:inline-block; mso-padding-alt:0;\">\n" +
                    "                                            <!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%;mso-text-raise:20pt\">&nbsp;</i><![endif]--><span\n" +
                    "                                                style=\"mso-text-raise:10pt;font-weight:bold;\">Read more</span>\n" +
                    "                                            <!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%\">&nbsp;</i><![endif]-->\n" +
                    "                                        </a></p>\n" +
                    "                                </div>\n" +
                    "                      \n" +
                    "                            </td>\n" +
                    "                        </tr>";

            html.append(newsHTML);

            html.append("<tr>\n" +
                    "                            <td style=\"padding:8px;font-size:24px;line-height:28px;font-weight:bold;\">\n" +
                    "                                \n" +
                    "                            </td>\n" +
                    "                        </tr>");
        }

        html.append("                        \n" +
                "                       <tr>\n" +
                "                            <td style=\"padding:30px;background-color:rgba(255, 255, 255, 0.137);\">\n" +
                "                                <p style=\"font-size:16px;padding-top:16px;padding-bottom: 16px;\" align=\"center\">\n" +
                "                                    <a href=\"http://covid-app-bucket.s3-website.us-east-2.amazonaws.com/countries/all\"\n" +
                "                                        style=\"background: #03DAC5; text-decoration: none; padding: 10px 25px; color: #ffffff; border-radius: 4px; display:inline-block; mso-padding-alt:0;\">\n" +
                "                                        <!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%;mso-text-raise:20pt\">&nbsp;</i><![endif]--><span\n" +
                "                                            style=\"mso-text-raise:10pt;font-weight:bold;\">Click for more Coronavirus statistics</span>\n" +
                "                                        <!--[if mso]><i style=\"letter-spacing: 25px;mso-font-width:-100%\">&nbsp;</i><![endif]-->\n" +
                "                                    </a>\n" +
                "                                </p>\n" +
                "                            </td>\n" +
                "                        </tr>" +
                "                            <td\n" +
                "                                style=\"padding:30px;text-align:center;font-size:12px;background-color:#404040;color:#cccccc;\">\n" +
                "                                <p style=\"margin:0 0 8px 0;\"><a href=\"http://www.github.com/praneshr2000\"\n" +
                "                                        style=\"text-decoration:none;\"><img\n" +
                "                                            src=\"https://icon-library.com/images/github-icon-png/github-icon-png-29.jpg\" width=\"40\" height=\"40\"\n" +
                "                                            alt=\"f\" style=\"display:inline-block;color:#cccccc;\"></a> <a\n" +
                "                                        href=\"mailto:praneshreddy2000@gmail.com\" style=\"text-decoration:none;\"><img\n" +
                "                                            src=\"https://www.freeiconspng.com/thumbs/gmail-icon/gmail-icon-23.png\" width=\"40\" height=\"40\"\n" +
                "                                            alt=\"t\" style=\"display:inline-block;color:#cccccc;\"></a></p>\n" +
                "                                <p style=\"margin:0;font-size:14px;line-height:20px;\"><br><a\n" +
                "                                        class=\"unsub\" href=\"http://covid-app-bucket.s3-website.us-east-2.amazonaws.com/mailing_list/unsubscribe\"\n" +
                "                                        style=\"color:#cccccc;text-decoration:underline;\">Unsubscribe from the newsletter</a></p>\n" +
                "                            </td>\n" +
                "                        </tr>" +
                "                    </table>\n" +
                "                  \n" +
                "                </td>\n" +
                "            </tr>\n" +
                "        </table>\n" +
                "    </div>\n" +
                "</body>\n" +
                "\n" +
                "</html>\n");

        return html.toString();
    }

    public Map<Integer, List<String>> buildNewsContent(JSONObject jsonNews) throws JSONException {
        Map<Integer, List<String>> newsNumberToContent = new TreeMap<>();

        JSONArray articlesArray = jsonNews.getJSONArray("articles");
        for (int i = 0; i < 20; i++) {
            JSONObject currObject = articlesArray.getJSONObject(i);
            String link = currObject.getString("link");
            String title = currObject.getString("title");
            String media = currObject.getString("media");
            String summary = currObject.getString("summary");
            String excerpt = currObject.getString("excerpt");

            if (link != null && title != null && media != null && (summary != null || excerpt != null)) {
                List<String> list = new ArrayList<>();
                list.add(title);
                list.add(link);
                list.add(media);
                list.add(Objects.requireNonNullElse(excerpt, summary));
                newsNumberToContent.put(i, list);
            }
        }

        return newsNumberToContent;
    }
}

using System;
using System.Collections;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Script.Serialization;
using System.Web.Services;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace VerticalSearch
{
    public partial class verticalSearchAssessment : System.Web.UI.Page
    {
        public static Dictionary<String,String> assessmentDict = new Dictionary<String,String>();
        static String qrelFilePath = HttpContext.Current.Server.MapPath("data\\qrels.txt");

        protected void Page_Load(object sender, EventArgs e)
        {

        }

        [WebMethod]
        public static String storeAssessment(String jsonResponse, String queryId, String assessorId)
        {
            parseJsonResponseToDictionary(jsonResponse);
            using (StreamWriter writer = new StreamWriter(qrelFilePath, true))
            {
                foreach (KeyValuePair<string, string> entry in assessmentDict)
                {
                    writer.WriteLine(queryId + " " + assessorId + " " + entry.Key + " " + entry.Value);
                }
            }
            assessmentDict.Clear();
            return "Done";
        }

        public static void parseJsonResponseToDictionary(String jsonResponse)
        {
            var json = new JavaScriptSerializer();
            assessmentDict = json.Deserialize<Dictionary<String, String>>(jsonResponse);
        }
    }
}
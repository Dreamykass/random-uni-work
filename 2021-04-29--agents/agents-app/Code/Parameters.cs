using System;
using System.Collections.Generic;
using System.Diagnostics;

namespace agents_app.Code
{
    public class Parameters
    {
        // parameters = 0.0 to 10.0
        // priorities = 0.0 to 1.0
        
        public int Cost { get; set; } = 2000;

        public float Gaming { get; set; } = 1;
        public float GamingPriority { get; set; } = 1;

        public float Working { get; set; } = 1;
        public float WorkingPriority { get; set; } = 1;

        public float Movies { get; set; } = 1;
        public float MoviesPriority { get; set; } = 1;

        public float Browsing { get; set; } = 1;
        public float BrowsingPriority { get; set; } = 1;

        public float Upgrading { get; set; } = 1;
        public float UpgradingPriority { get; set; } = 1;

        public float Portability { get; set; } = 1;
        public float PortabilityPriority { get; set; } = 1;

        public float Knowledge { get; set; } = 1;
        public float KnowledgePriority { get; set; } = 1;

        public static List<string> DictionaryKeys()
        {
            var list = new List<string>();
            list.Add("Gaming");
            list.Add("Working");
            list.Add("Movies");
            list.Add("Browsing");
            list.Add("Upgrading");
            list.Add("Portability");
            list.Add("Knowledge");
            return list;
        }

        public Dictionary<string, float> ParametersToDictionary()
        {
            var dictionary = new Dictionary<string, float>();
            dictionary.Add("Gaming", Gaming);
            dictionary.Add("Working", Working);
            dictionary.Add("Movies", Movies);
            dictionary.Add("Browsing", Browsing);
            dictionary.Add("Upgrading", Upgrading);
            dictionary.Add("Portability", Portability);
            dictionary.Add("Knowledge", Knowledge);
            return dictionary;
        }

        public Dictionary<string, float> PrioritiesToDictionary()
        {
            var dictionary = new Dictionary<string, float>();
            dictionary.Add("Gaming", GamingPriority);
            dictionary.Add("Working", WorkingPriority);
            dictionary.Add("Movies", MoviesPriority);
            dictionary.Add("Browsing", BrowsingPriority);
            dictionary.Add("Upgrading", UpgradingPriority);
            dictionary.Add("Portability", PortabilityPriority);
            dictionary.Add("Knowledge", KnowledgePriority);
            return dictionary;
        }

        // 0.0 = perfect
        public float DivergenceFromUserInputParameters(Parameters userInputParameters)
        {
            var likeness = 0.0f;
            var computerParameters = ParametersToDictionary();
            var userParameters = userInputParameters.ParametersToDictionary();
            var userPriorities = userInputParameters.PrioritiesToDictionary();
            var dictionaryKeys = DictionaryKeys();

            foreach (var dictionaryKey in dictionaryKeys)
            {
                var computerParameter = computerParameters[dictionaryKey];
                var userParameter = userParameters[dictionaryKey];
                var userPriority = userPriorities[dictionaryKey];
                
                // asserts:
                Trace.Assert(computerParameter >= 0.0 && computerParameter <= 10.0);
                Trace.Assert(userParameter >= 0.0 && userParameter <= 10.0);
                Trace.Assert(userPriority >= 0.0 && userPriority <= 1.0);
                
                likeness += Math.Abs(computerParameter - userParameter) * userPriority;
            }

            return likeness;
        }
    }
}
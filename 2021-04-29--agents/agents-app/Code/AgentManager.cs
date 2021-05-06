using System;
using System.Collections.Generic;
using System.Linq;

namespace agents_app.Code
{
    public static class AgentManager
    {
        public static int foo = 5;
        public static List<Agent> agents = new();

        static AgentManager()
        {
            // agents.Add();
        }

        public static void StartSearching(Parameters parameters)
        {
            
        }

        public static List<String> GetFoundComputers()
        {
            string[] computers = {"All in one HP 16GB", "ASUS VivoStick", "Apple Mac Mini", "Dell Vostro", "Shiru 7200"}; 
            return computers.Shuffle().Take(4).ToList();
        } 
    }
}
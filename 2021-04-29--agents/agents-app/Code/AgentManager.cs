using System;
using System.Collections.Generic;
using System.Linq;

namespace agents_app.Code
{
    public static class AgentManager
    {
        public static int foo = 5;

        public static int GetFoo()
        {
            return foo;
        }

        public static void SetFoo(int i)
        {
            foo = i;
        }

        public static void StartSearching(int game, int work, int movie, int net, int up, int port, int know)
        {
            
        }

        public static List<String> GetFoundComputers()
        {
            string[] computers = {"All in one HP 16GB", "ASUS VivoStick", "Apple Mac Mini", "Dell Vostro", "Shiru 7200"}; 
            return computers.Shuffle().Take(4).ToList();
        } 
    }
}
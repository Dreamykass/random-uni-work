using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Text.Json;

namespace agents_app.Code
{
    public class Computer
    {
        public string Name { get; set; } = "undefined";
        public string Link { get; set; } = "google.com";
        public Parameters Parameters { get; set; } = null;

        private static List<Computer> AllComputers { get; }

        static Computer()
        {
            var json = File.ReadAllText("computers.json");
            var list = JsonSerializer.Deserialize<List<Computer>>(json);
            Trace.Assert(list != null);
            list.RemoveAll(c => c.Name.Contains("undefined"));
            AllComputers = list;
        }

        public static List<Computer> GetAllComputers()
        {
            return AllComputers;
        }
    }
}
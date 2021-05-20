
using System.Collections.Generic;

namespace agents_app.Code
{
    public class Computer
    {
        public string Name;
        public int Cost;

        public Computer(string name, int cost)
        {
            Name = name;
            Cost = cost;
        }

        public static List<Computer> GetAllComputers()
        {
            var list = new List<Computer>();
            list.Add(new Computer("All in one HP 16GB", 7000));
            list.Add(new Computer("ASUS VivoStick", 8000));
            list.Add(new Computer("Apple Mac Mini", 99999));
            list.Add(new Computer("Dell Vostro", 82000));
            list.Add(new Computer("Shiru 7200", 12000));
            return list;
        }
    }
}
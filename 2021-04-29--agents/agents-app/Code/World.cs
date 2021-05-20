using System.Collections.Generic;
using System.Linq;

namespace agents_app.Code
{
    public static class World
    {
        public static List<Buyer> Buyers { get; set; } = new();
        public static List<Seller> Sellers { get; set; } = new();
        public static Parameters UserInputParameters { get; set; } = null;

        static World()
        {
            Buyers.Add(new Buyer());
            Buyers.Add(new Buyer());
            Buyers.Add(new Buyer());
            Buyers.Add(new Buyer());
            Buyers.Add(new Buyer());
            Buyers.Add(new Buyer());
            
            Sellers.Add(new Seller());
            Sellers.Add(new Seller());
            Sellers.Add(new Seller());
            Sellers.Add(new Seller());
            Sellers.Add(new Seller());
            Sellers.Add(new Seller());
            Sellers.Add(new Seller());
            Sellers.Add(new Seller());
        }

        public static void SetParameters(Parameters parameters)
        {
            UserInputParameters = parameters;
        }

        public static IEnumerable<Computer> GetFoundComputers()
        {
            return Buyers.Select(agent => agent.GetComputer()).ToList();
        }

        public static void GiveFeedback(Computer chosenComputer)
        {
            Buyers.ForEach(a => a.GiveFeedback(chosenComputer));
        }
    }
}
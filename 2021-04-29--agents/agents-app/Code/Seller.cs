using System;
using System.Collections.Generic;

namespace agents_app.Code
{
    public class Seller
    {
        // map<actual cost, computer>
        public List<Computer> ComputersInStock { get; set; } = new();

        public Seller()
        {
            var allComputers = Computer.GetAllComputers();
            var random = new Random();

            for (int i = 0; i < 10; i++)
            {
                var computer = allComputers[random.Next(0, allComputers.Count)].DeepClone();

                var cost = computer.Parameters.Cost;
                var costDif = cost * 0.1;
                cost += random.Next((int) -costDif, (int) costDif);
                computer.Parameters.Cost = cost;

                ComputersInStock.Add(computer);
            }

            // ComputersInStock.Sort((c1, c2) => c1.Parameters.Cost.CompareTo(c2.Parameters.Cost));
            ComputersInStock.Sort((c1, c2) => string.Compare(c1.Name, c2.Name, StringComparison.Ordinal));
        }

        public List<Computer> SuggestComputersForParameters(Parameters parameters)
        {
            var list = new List<Computer>();
            foreach (var computer in ComputersInStock)
            {
                if (computer.Parameters.Cost <= World.UserInputParameters.Cost)
                    list.Add(computer);
            }

            list.Shuffle();
            return list;
        }
    }
}
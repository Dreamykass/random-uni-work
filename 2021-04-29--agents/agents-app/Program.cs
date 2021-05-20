using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.Hosting;

namespace agents_app
{
    public class Program
    {
        public static void Main(string[] args)
        {
            // var c = new Computer();
            // c.Parameters = new Parameters();
            // var l = new List<Computer>();
            // l.Add(c);
            // l.Add(c);
            // var jsonString = JsonSerializer.Serialize(l);
            // File.WriteAllText("computers2.json", jsonString);

            CreateHostBuilder(args).Build().Run();
        }

        public static IHostBuilder CreateHostBuilder(string[] args)
        {
            return Host.CreateDefaultBuilder(args)
                .ConfigureWebHostDefaults(webBuilder => { webBuilder.UseStartup<Startup>(); });
        }
    }
}
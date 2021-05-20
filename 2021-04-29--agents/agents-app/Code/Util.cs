using System.IO;
using System.Runtime.Serialization.Formatters.Binary;
using System.Text.Json;

namespace agents_app.Code
{
    public static class Util
    {
        public static T DeepClone<T>(this T obj)
        {
            var json = JsonSerializer.Serialize(obj);
            return JsonSerializer.Deserialize<T>(json);
        }
    }
}
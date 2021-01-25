using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Support.Design.Widget;
using Android.Views;
using Android.Widget;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CheckersApp
{
	public class ToastSnackUtil
	{
		static View sender = null;

		public static void MakeToast(String message, bool lengthVeryLong)
		{
			//View view = (View)sender;
			//Snackbar.Make(view, text, Snackbar.LengthLong)
			//	.SetAction("Action", (Android.Views.View.IOnClickListener)null)
			//	.Show();

			if (lengthVeryLong)
				Toast.MakeText(Application.Context, message, ToastLength.Long).Show();
			else
				Toast.MakeText(Application.Context, message, ToastLength.Short).Show();
		}

		public static void BindSnackSender(View s)
		{
			sender = s;
		}

		public static void MakeSnack(String message, bool lengthVeryLong)
		{
			View view = sender;

			if (lengthVeryLong)
				Snackbar.Make(view, message, Snackbar.LengthLong)
				.SetAction("Action", (Android.Views.View.IOnClickListener)null)
				.Show();
			else
				Snackbar.Make(view, message, Snackbar.LengthShort)
				.SetAction("Action", (Android.Views.View.IOnClickListener)null)
				.Show();

		}

	}
}
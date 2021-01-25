using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Views;
using Android.Widget;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CheckersApp
{
	enum FieldState
	{
		Empty,
		PawnWhiteTop,
		PawnBlackBot,
		LadyWhiteTop,
		LadyBlackBot,

		WhiteNonPlayable,
	}

	class Field
	{
		private readonly Context context;
		public ImageButton imageButton;
		public FieldState state = FieldState.WhiteNonPlayable;
		public bool playable = false;
		public (int, int) location = (-1, -1);
		public Player? player = null;
		public bool ladyStatus = false;

		public Field(Context c)
		{
			context = c;
		}

		public void AdjustImageButton()
		{
			switch (state)
			{
				case FieldState.Empty:
					{
						imageButton.SetImageDrawable(context.GetDrawable(Resource.Drawable.Empty));
						break;
					}

				case FieldState.PawnBlackBot:
					{
						imageButton.SetImageDrawable(context.GetDrawable(Resource.Drawable.PawnBlackBot));
						break;
					}
				case FieldState.PawnWhiteTop:
					{
						imageButton.SetImageDrawable(context.GetDrawable(Resource.Drawable.PawnWhiteTop));
						break;
					}

				case FieldState.LadyBlackBot:
					{
						imageButton.SetImageDrawable(context.GetDrawable(Resource.Drawable.LadyBlackBot));
						break;
					}
				case FieldState.LadyWhiteTop:
					{
						imageButton.SetImageDrawable(context.GetDrawable(Resource.Drawable.LadyWhiteTop));
						break;
					}

				case FieldState.WhiteNonPlayable:
					{
						imageButton.SetImageDrawable(context.GetDrawable(Resource.Drawable.WhiteNonPlayable));
						break;
					}
			}

		}

		public void TemporarilyTintOnClick()
		{
			imageButton.SetColorFilter(Android.Graphics.Color.Green, Android.Graphics.PorterDuff.Mode.Add);
			Task.Run(async delegate
			{
				await Task.Delay(600);
				imageButton.ClearColorFilter();
				await Task.Delay(200);
				imageButton.SetColorFilter(Android.Graphics.Color.Green, Android.Graphics.PorterDuff.Mode.Add);
				await Task.Delay(600);
				imageButton.ClearColorFilter();
			});
		}

		public void NowSelected()
		{
			imageButton.SetColorFilter(Android.Graphics.Color.Red, Android.Graphics.PorterDuff.Mode.Add);
		}

		public void Unselected()
		{
			imageButton.ClearColorFilter();

		}
	}
}
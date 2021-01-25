using Android.App;
using Android.Content;
using Android.OS;
using Android.Runtime;
using Android.Util;
using Android.Views;
using Android.Widget;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CheckersApp
{
	public enum Player
	{
		WhiteTop,
		BlackBot,
	}

	[Activity(Label = "Activity1")]
	public class GameActivity : Activity
	{
		bool finished = false;
		GridLayout gridLayoutMap = null;
		TextView textViewDebug = null;

		bool backwardsBeating = false;
		bool pickingPawnToKill = false;

		Dictionary<(int, int), Field> playableMap = new Dictionary<(int, int), Field>();
		Dictionary<(int, int), Field> fullMap = new Dictionary<(int, int), Field>();
		List<Field> fullMapList = new List<Field>();

		Dictionary<Player, List<Field>> playerPawnsLists = new Dictionary<Player, List<Field>>();
		Dictionary<Player, int> playerScores = new Dictionary<Player, int>();

		Player currentPlayer = Player.BlackBot;
		Field currentSelectedField = null;

		protected override void OnCreate(Bundle savedInstanceState)
		{
			base.OnCreate(savedInstanceState);

			backwardsBeating = Intent.GetBooleanExtra("backwardsBeating", false);
			SetContentView(Resource.Layout.activity_game);

			gridLayoutMap = FindViewById<GridLayout>(Resource.Id.gridLayoutMap);
			textViewDebug = FindViewById<TextView>(Resource.Id.textViewDebug);


			playerPawnsLists.Add(Player.BlackBot, new List<Field>());
			playerPawnsLists.Add(Player.WhiteTop, new List<Field>());

			playerScores.Add(Player.BlackBot, 0);
			playerScores.Add(Player.WhiteTop, 0);


			// fill maps and list
			for (int i = 0; i < 8; i++)
			{
				for (int j = 0; j < 8; j++)
				{
					ImageButton imageButton = new ImageButton(this);
					imageButton.SetImageDrawable(GetDrawable(Resource.Drawable.WhiteNonPlayable));
					imageButton.SetPadding(2, 2, 2, 2);
					gridLayoutMap.AddView(imageButton);

					Field field = new Field(this);
					field.imageButton = imageButton;

					fullMap.Add((i, j), field);
					fullMapList.Add(field);

					if (i % 2 == 0)
					{
						if (j % 2 == 0)
						{
							imageButton.SetImageDrawable(GetDrawable(Resource.Drawable.WhiteNonPlayable));
							field.state = FieldState.WhiteNonPlayable;
						}
						else
						{
							imageButton.SetImageDrawable(GetDrawable(Resource.Drawable.Empty));
							field.state = FieldState.Empty;
							playableMap.Add((i, j), field);
							field.playable = true;
						}
					}
					else
					{
						if (j % 2 == 1)
						{
							imageButton.SetImageDrawable(GetDrawable(Resource.Drawable.WhiteNonPlayable));
							field.state = FieldState.WhiteNonPlayable;
						}
						else
						{
							imageButton.SetImageDrawable(GetDrawable(Resource.Drawable.Empty));
							field.state = FieldState.Empty;
							playableMap.Add((i, j), field);
							field.playable = true;
						}
					}

				}
			}

			// add player fields
			foreach (Field f in playableMap.Values.Take(12))
			{
				f.state = FieldState.PawnWhiteTop;
				playerPawnsLists[Player.WhiteTop].Add(f);
				f.player = Player.WhiteTop;
			}
			foreach (Field f in playableMap.Values.Skip(12).Skip(8).Take(12))
			{
				f.state = FieldState.PawnBlackBot;
				playerPawnsLists[Player.BlackBot].Add(f);
				f.player = Player.BlackBot;
			}

			// adjust images
			foreach (Field f in playableMap.Values)
			{
				f.AdjustImageButton();
			}

			// set on clicks
			foreach (KeyValuePair<(int, int), Field> kpv in fullMap)
			{
				Field f = kpv.Value;
				f.location = kpv.Key;

				if (f.state == FieldState.WhiteNonPlayable)
				{
					f.imageButton.Click += delegate
					{
						NonPlayableOnClick(f);
					};
				}
				else
				{
					f.imageButton.Click += delegate
					{
						PlayableOnClick(f);
					};
				}
			}

			// other
			ToastSnackUtil.BindSnackSender(FindViewById(Android.Resource.Id.Content));
			UpdateHeaderText();
			UpdateDebug();

			// end game
			FindViewById<Button>(Resource.Id.buttonEndGame).Click += delegate
			{
				AlertDialog.Builder alertDiag = new AlertDialog.Builder(this);
				alertDiag.SetTitle("End of the game.");

				var scoreBlackBot = playerScores[Player.BlackBot];
				var scoreWhiteTop = playerScores[Player.WhiteTop];
				var message = "";
				if (scoreBlackBot < scoreWhiteTop)
					message = "The game was won by the top/white player.";
				else if (scoreBlackBot > scoreWhiteTop)
					message = "The game was won by the bottom/black player.";
				else
					message = "Draw!";

				alertDiag.SetMessage(message);

				alertDiag.SetPositiveButton("OK", (senderAlert, args) =>
				{
				});
				alertDiag.SetNegativeButton("OK", (senderAlert, args) =>
				{
				});
				Dialog diag = alertDiag.Create();
				diag.Show();

				finished = true;
				textViewDebug.Text = "Game over. " + message;
				var textViewTop = FindViewById<TextView>(Resource.Id.textViewTop);
				textViewTop.Text = "Game over. " + message;

				foreach (var f in fullMapList)
				{
					f.imageButton.Enabled = false;
				}

			};
			FindViewById<ToggleButton>(Resource.Id.toggleButton1).Click += delegate
			{
				var butt = FindViewById<ToggleButton>(Resource.Id.toggleButton1);
				if (butt.Checked)
				{
					pickingPawnToKill = true;
					ToastSnackUtil.MakeSnack("Select a pawn to remove.", true);
					//foreach (var f in fullMapList)
					//	f.TemporarilyTintOnClick();
				}
				else
				{
					pickingPawnToKill = false;
					ToastSnackUtil.MakeSnack("Play the game normally now.", true);
				}
			};
		}

		void NonPlayableOnClick(Field field)
		{
			//string str = string.Format("This is a non-playable field at {0}, owned by ({1}).", field.location, field.player);
			//ToastSnackUtil.MakeToast(str, true);
			ToastSnackUtil.MakeSnack("This is a nonplayable field.", true);
			field.TemporarilyTintOnClick();

		}
		void PlayableOnClick(Field clickedField)
		{
			UpdateDebug();

			//string str = string.Format("This is a playable field at {0}, owned by ({1}).", clickedField.location, clickedField.player);
			//ToastSnackUtil.MakeToast(str, true);

			if (finished)
				return;

			if (!pickingPawnToKill)
			{
				// player clicks own pawn while nothing is selected
				if (currentSelectedField == null && clickedField.player == currentPlayer)
				{
					currentSelectedField = clickedField;
					ToastSnackUtil.MakeSnack("Selected your pawn (a "
						+ currentSelectedField.state + ") (none was selected previously) at " + currentSelectedField.location + ".", true);
					currentSelectedField.NowSelected();
				}
				// player clicks own pawn otherwise
				else if (currentSelectedField != null && clickedField.player == currentPlayer)
				{
					currentSelectedField.Unselected();
					currentSelectedField = clickedField;
					currentSelectedField.NowSelected();
					ToastSnackUtil.MakeSnack("Selected your pawn (a " + currentSelectedField.state + ") at " + currentSelectedField.location + ".", true);
				}
				// player clicks empty playable while pawn is selected
				else if (currentSelectedField != null && clickedField.state == FieldState.Empty)
				{
					if (CheckIfValidShortMove(currentSelectedField, clickedField))
					{
						ToastSnackUtil.MakeSnack("Move is valid, moved the pawn.", true);
						Util.Swap(ref currentSelectedField.state, ref clickedField.state);
						SwitchPlayerAndResetTheBoard();
						PromotePawnsToLadies();
					}
					else if (CheckIfValidLongKillingMove(currentSelectedField, clickedField))
					{
						ToastSnackUtil.MakeSnack("Move is valid, moved the pawn and killed enemys pawn.", true);
						{
							var currentNeighbors = GetPlayableNeighbors(currentSelectedField);
							var moveNeighbors = GetPlayableNeighbors(clickedField);
							var intersect = currentNeighbors.Intersect(moveNeighbors);
							var killable = intersect.First();

							if (killable.ladyStatus)
								playerScores[currentPlayer] += 2;
							else
								playerScores[currentPlayer] += 1;

							killable.state = FieldState.Empty;
							//killable.TemporarilyTintOnClick();
							//currentSelectedField.TemporarilyTintOnClick();
							//clickedField.TemporarilyTintOnClick();
						}
						Util.Swap(ref currentSelectedField.state, ref clickedField.state);
						SwitchPlayerAndResetTheBoard();
					}
					else if (currentSelectedField.ladyStatus && MaybeDoLadyMove(currentSelectedField, clickedField))
					{
						ToastSnackUtil.MakeSnack("Move is valid, moved the lady (and killed any enemys pawns on the way).", true);
						Util.Swap(ref currentSelectedField.state, ref clickedField.state);
						SwitchPlayerAndResetTheBoard();
					}
					else
					{
						ToastSnackUtil.MakeSnack("Move is NOT valid. Pick another one.", true);
						clickedField.TemporarilyTintOnClick();
					}
				}
				// otherwise
				else
				{
					ToastSnackUtil.MakeSnack("What are you even clicking at " + clickedField.location + ". Wrong.", true);
					clickedField.TemporarilyTintOnClick();
				}
			}
			else
			{
				if (clickedField.state != FieldState.Empty)
				{
					switch (clickedField.state)
					{
						case FieldState.PawnBlackBot:
							{
								playerScores[Player.BlackBot] += 1;
								break;
							}
						case FieldState.PawnWhiteTop:
							{
								playerScores[Player.WhiteTop] += 1;
								break;
							}
						case FieldState.LadyBlackBot:
							{
								playerScores[Player.BlackBot] += 2;
								break;
							}
						case FieldState.LadyWhiteTop:
							{
								playerScores[Player.WhiteTop] += 2;
								break;
							}
					}
					clickedField.player = null;
					clickedField.state = FieldState.Empty;
				}

				foreach (Field f in fullMap.Values)
				{
					f.AdjustImageButton();
					f.Unselected();

					if (f.state == FieldState.LadyBlackBot || f.state == FieldState.PawnBlackBot)
						f.player = Player.BlackBot;
					else if (f.state == FieldState.LadyWhiteTop || f.state == FieldState.PawnWhiteTop)
						f.player = Player.WhiteTop;
					else
						f.player = null;

					if (f.state == FieldState.LadyBlackBot || f.state == FieldState.LadyWhiteTop)
					{
						f.ladyStatus = true;
					}
					else
					{
						f.ladyStatus = false;
					}
				}

				currentSelectedField = null;
				UpdateHeaderText();
				ToastSnackUtil.MakeSnack("Removed a pawn.", true);
			}

			UpdateDebug();
		}

		bool CheckIfValidShortMove(Field current, Field move)
		{
			Log.Error("wtf CheckIfValidShortMove", "current: {0}, against: {1}", current.location, move.location);

			if (currentPlayer == Player.BlackBot)
			{
				if (move.location.Item1 + 1 == current.location.Item1
					&& (move.location.Item2 - 1 == current.location.Item2
					 || move.location.Item2 + 1 == current.location.Item2))
				{
					Log.Error("wtf", "if (currentPlayer == Player.BlackBot) true");
					return true;
				}
			}
			else if (currentPlayer == Player.WhiteTop)
			{
				if (move.location.Item1 - 1 == current.location.Item1
					&& (move.location.Item2 - 1 == current.location.Item2
					 || move.location.Item2 + 1 == current.location.Item2))
				{
					Log.Error("wtf", "else if (currentPlayer == Player.WhiteTop) true");
					return true;
				}
			}
			else if (current.ladyStatus == true
				&& GetPlayableNeighbors(current)
				.Where(f => f.state == FieldState.Empty)
				.Where(f => f == move)
				.Any())
			{
				Log.Error("wtf", "else if (current.ladyStatus == true ...) true");
				return true;
			}

			Log.Error("wtf", "nope");
			return false;
		}
		bool CheckIfValidLongKillingMove(Field current, Field move)
		{
			Log.Error("wtf CheckIfValidLongKillingMove", "current: {0}, against: {1}", current.location, move.location);

			var currentNeighbors = GetPlayableNeighbors(current);
			var moveNeighbors = GetPlayableNeighbors(move);
			var intersect = currentNeighbors.Intersect(moveNeighbors);

			if (intersect.Any())
			{
				Log.Error("wtf", "between is: {0} at {1}", intersect.First().player, intersect.First().location);
			}

			if (backwardsBeating || current.ladyStatus)
			{
				Log.Error("wtf", "backwardsBeating || current.ladyStatus");

				if (intersect.Any() && intersect.First().player != null && intersect.First().player != currentPlayer)
				{
					Log.Error("wtf", "true");
					return true;
				}
			}
			else
			{
				Log.Error("wtf", "else");

				if (intersect.Any() && intersect.First().player != null && intersect.First().player != currentPlayer
					&& (
						(currentPlayer == Player.BlackBot && move.location.Item1 < current.location.Item1)
						|| (currentPlayer == Player.WhiteTop && move.location.Item1 > current.location.Item1)
					))
				{
					Log.Error("wtf", "true");
					return true;
				}
			}

			return false;
		}
		bool MaybeDoLadyMove(Field current, Field move)
		{
			Log.Error("wtf MaybeDoLadyMove", "current: {0}, against: {1}", current.location, move.location);

			var diff = (Math.Clamp((current.location.Item1 - move.location.Item1) * -1, -1, 1),
				Math.Clamp((current.location.Item2 - move.location.Item2) * -1, -1, 1));

			Log.Error("wtf", "diff is: {0}", diff);


			var loc = (current.location.Item1, current.location.Item2);
			while (playableMap.ContainsKey((loc.Item1 + diff.Item1, loc.Item2 + diff.Item2))
				&& loc != move.location)
			{
				loc = (loc.Item1 + diff.Item1, loc.Item2 + diff.Item2);

				Log.Error("wtf", "...checking: {0} at {1}", playableMap[loc].state, playableMap[loc].location);

				if (playableMap[loc].player == currentPlayer)
				{
					Log.Error("wtf", "if (playableMap[loc].player == currentPlayer) false");
					return false;
				}
			}

			loc = (current.location.Item1, current.location.Item2);
			while (playableMap.ContainsKey((loc.Item1 + diff.Item1, loc.Item2 + diff.Item2))
				&& loc != move.location)
			{
				loc = (loc.Item1 + diff.Item1, loc.Item2 + diff.Item2);
				playableMap[loc].state = FieldState.Empty;
			}

			Log.Error("wtf", "true");
			return true;
		}

		List<Field> GetPlayableNeighbors(Field field)
		{
			List<Field> neighbors = new List<Field>();
			foreach (Field other in playableMap.Values)
			{
				if (other.location.Item1 + 1 == field.location.Item1)
				{
					if (other.location.Item2 + 1 == field.location.Item2
						|| other.location.Item2 - 1 == field.location.Item2)
					{
						neighbors.Add(other);
					}
				}
				else if (other.location.Item1 - 1 == field.location.Item1)
				{
					if (other.location.Item2 + 1 == field.location.Item2
						|| other.location.Item2 - 1 == field.location.Item2)
					{
						neighbors.Add(other);
					}
				}
			}
			return neighbors;
		}

		void SwitchPlayerAndResetTheBoard()
		{
			if (currentPlayer == Player.BlackBot)
				currentPlayer = Player.WhiteTop;
			else
				currentPlayer = Player.BlackBot;

			foreach (Field f in fullMap.Values)
			{
				f.AdjustImageButton();
				f.Unselected();

				if (f.state == FieldState.LadyBlackBot || f.state == FieldState.PawnBlackBot)
					f.player = Player.BlackBot;
				else if (f.state == FieldState.LadyWhiteTop || f.state == FieldState.PawnWhiteTop)
					f.player = Player.WhiteTop;
				else
					f.player = null;

				if (f.state == FieldState.LadyBlackBot || f.state == FieldState.LadyWhiteTop)
				{
					f.ladyStatus = true;
				}
				else
				{
					f.ladyStatus = false;
				}
			}

			currentSelectedField = null;

			PromotePawnsToLadies();
			UpdateHeaderText();
		}
		void PromotePawnsToLadies()
		{
			foreach (var pawn in playableMap.Values
				.Where((Field f) => f.state == FieldState.PawnBlackBot || f.state == FieldState.PawnWhiteTop))
			{
				if (pawn.player == Player.BlackBot && pawn.location.Item1 == 0)
				{
					pawn.state = FieldState.LadyBlackBot;
					pawn.ladyStatus = true;
				}
				else if (pawn.player == Player.WhiteTop && pawn.location.Item1 == 7)
				{
					pawn.state = FieldState.LadyWhiteTop;
					pawn.ladyStatus = true;
				}


				pawn.AdjustImageButton();
			}
		}
		void UpdateHeaderText()
		{
			var textView = FindViewById<TextView>(Resource.Id.textViewTop);
			var text = String.Format(
				"\nCurrent player: {0} \nBlack/Bottom score: {1}, \nWhite/Top score: {2} \n",
				currentPlayer,
				playerScores[Player.BlackBot],
				playerScores[Player.WhiteTop]
				);
			textView.Text = text;

			UpdateDebug();
		}
		void UpdateDebug()
		{
			if (currentSelectedField != null)
				textViewDebug.Text = String.Format("Currently selected: {0} at {1} (lady = {2})\n",
					currentSelectedField.state,
					currentSelectedField.location,
					currentSelectedField.ladyStatus);
			else
				textViewDebug.Text = String.Format("Currently selected: none\n");
		}

	}
}
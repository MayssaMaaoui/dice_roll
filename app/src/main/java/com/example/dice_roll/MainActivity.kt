package com.example.dice_roll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dice_roll.ViewModel.DiceViewModel
//import com.example.dice_roll.ui.theme.DiceRollTheme

class MainActivity : ComponentActivity() {
    private val viewModel: DiceViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DiceRollApp(viewModel)
        }
    }
}

@Composable
fun DiceRollApp(viewModel: DiceViewModel) {
    var diceRoll by remember { mutableStateOf(1) }

    // Observer le score du joueur depuis le ViewModel
    val playerScore by viewModel.player1Points.observeAsState(0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Afficher l'image correspondant au résultat du dé
        Image(
            painter = painterResource(id = getDiceImageResource(diceRoll)),
            contentDescription = "Résultat du dé",
            modifier = Modifier.size(150.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Score du Joueur: $playerScore", fontSize = 18.sp)

        Button(onClick = {
            viewModel.rollDice(1)
            diceRoll = (1..6).random()  // Mettre à jour l'image du dé
        }) {
            Text("Lancer le dé")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            viewModel.resetGame()
            diceRoll = 1  // Réinitialiser l'image du dé
        }) {
            Text("Réinitialiser")
        }
    }
}

// Fonction utilitaire pour récupérer l'image correspondant à la valeur du dé
fun getDiceImageResource(diceRoll: Int): Int {
    return when (diceRoll) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        6 -> R.drawable.dice_6
        else -> R.drawable.dice_1
    }
}

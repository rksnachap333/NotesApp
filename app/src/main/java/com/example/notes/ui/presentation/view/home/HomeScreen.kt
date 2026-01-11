import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.notes.ui.presentation.navigation.NavigationManager
import com.example.notes.ui.presentation.navigation.NavigationState
import com.example.notes.ui.presentation.view.home.NotesGrid
import com.example.notes.ui.presentation.viewmodels.home.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    navigationManager: NavigationManager,
    viewModel: HomeViewModel = viewModel(),
    onAddClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = onAddClick) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add Note"
                )
            }
        }
    ) { padding ->

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.notes.isEmpty() -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Spacer(Modifier.height(8.dp))
                    Column(
                        modifier = Modifier.padding(padding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ){
                        Text(
                            "NOTES",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                            )
                        )
                        Text("No notes yet")
                    }

                }
            }

            else -> {
                Column(
                    modifier = Modifier.padding(padding),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ){
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "NOTES",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                        )
                    )
                    NotesGrid(
                        notes = state.notes,
                        modifier = Modifier.padding(padding),
                        onNoteClick = { note ->
                            navigationManager.navigateTo(NavigationState.ViewNote(note.id))
                        },
                        onSendIntent = { intent ->
                            viewModel.sendIntent(intent)
                        }
                    )
                }

            }
        }
    }
}

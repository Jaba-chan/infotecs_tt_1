package ru.evgenykuzakov.infotecs_tt_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.evgenykuzakov.designsystem.ui.LabelSmallText
import ru.evgenykuzakov.designsystem.ui.theme.Infotecs_TT_1_Theme
import ru.evgenykuzakov.infotecs_tt_1.navigation.AppNavGraph
import ru.evgenykuzakov.infotecs_tt_1.navigation.NavigationState
import ru.evgenykuzakov.infotecs_tt_1.navigation.Screen
import ru.evgenykuzakov.users.show_users.ShowUsersScreen
import ru.evgenykuzakov.users.user_detail_info.UserDetailInfoScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Infotecs_TT_1_Theme {
                val navController = rememberNavController()
                val navState = remember { NavigationState(navController) }
                val navBackStackEntry by navController.currentBackStackEntryAsState()

                val currentRoute = navBackStackEntry?.destination?.route
                var onRefreshClick by remember { mutableStateOf<() -> Unit>({}) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    floatingActionButton = {
                        if (currentRoute == Screen.ShowUsersScreen.route)
                            FloatingActionButton(
                                onClick = onRefreshClick,
                                modifier = Modifier.wrapContentWidth(),
                                containerColor = MaterialTheme.colorScheme.primary
                            ) {
                                LabelSmallText(
                                    modifier = Modifier
                                        .wrapContentWidth()
                                        .padding(horizontal = 28.dp),
                                    text = stringResource(R.string.refresh)
                                )
                            }
                    },
                    floatingActionButtonPosition = FabPosition.Center,
                ) { innerPadding ->
                    AppNavGraph(
                        navHostController = navController,
                        showUsersScreenContent = {
                            ShowUsersScreen(
                                paddingValues = innerPadding,
                                onUserClick = {
                                    navState.navigateTo(Screen.UserDetailInfoScreen.createRoute(it))
                                },
                                onRefreshClick = { onRefreshClick = it }
                            )
                        },
                        userDetailInfoScreenContent = {
                            UserDetailInfoScreen(
                                paddingValues = innerPadding,
                                onBackClicked = { navState.navigateTo(Screen.ShowUsersScreen.route) }
                            )
                        }
                    )
                }
            }
        }
    }
}

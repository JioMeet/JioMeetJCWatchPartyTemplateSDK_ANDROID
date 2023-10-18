package com.example.sample_app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.jio.sdk.LaunchWatchParty
import org.jio.sdk.common.navigation.WatchPartyNav
import org.jio.sdk.conference.model.WatchPartyData
import org.jio.sdk.conference.model.WatchPartyListener
import org.jio.sdk.sdkmanager.JCConnectionListener
import org.jio.sdk.utils.analytics.AnalyticsEvent


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val jioMeetConnectionListener = object : JCConnectionListener {

        override fun onShareInviteClicked(meetingId: String, meetingPin: String, name: String) {

        }

        override fun closeWatchParty() {
        }

        override fun onAnalyticsEvent(analyticsEvent: AnalyticsEvent) {
        }

        override fun showNativeLogin(isShow: Boolean) {
        }

        override fun onMediaVolumeChange(volume: Int) {
        }

        override fun partyStarted(meetingId: String) {

        }

    }

    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.hide()
        setContent {
            Column {
                val height = LocalConfiguration.current.screenHeightDp
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(((35.5f * height) / 100f).dp)
                )
                TabLayout()
            }
        }
    }
    @ExperimentalPagerApi
    @Composable
    fun TabLayout() {
        val pagerState = rememberPagerState()
        Column(
            modifier = Modifier.background(Color.White)
        ) {
            Tabs(pagerState = pagerState)
            TabsContent(pagerState = pagerState)
        }
    }

    @ExperimentalPagerApi
    @Composable
    fun Tabs(pagerState: PagerState) {
        val list = listOf(
            "Main",
            "WatchParty",
            "Game"
        )
        val scope = rememberCoroutineScope()
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            backgroundColor = Color.Black,
            contentColor = Color.White,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                    height = 2.dp,
                    color = Color.White
                )
            }
        ) {
            list.forEachIndexed { index, item ->
                Tab(
                    selected = pagerState.currentPage == index,
                    onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = {
                        Text(
                            text = item,
                            color = if (pagerState.currentPage == index) Color.White else Color.LightGray
                        )
                    },
                )
            }
        }
    }

    @ExperimentalPagerApi
    @Composable
    fun TabsContent(pagerState: PagerState) {
        val watchPartyListener = remember {
            mutableStateOf(WatchPartyListener(jioMeetConnectionListener))
        }
        HorizontalPager(count = 3, state = pagerState) { page ->
            when (page) {
                0 -> TabContentScreen(data = "Welcome to Main Screen")
                1 -> WatchPartyNav(
                    modifier = Modifier,
            watchPartyData = WatchPartyData(
                //pass clientToken
                clientToken = "",
                meetingID = "", //pass empty meeting id/pass to start watchparty
                isUserJoiner = false,
                meetingPin = "",
                partyGuestName = "",
                isLoggedInUser = false,
                userName = "Test",
                watchPartyListener = watchPartyListener,
            ),
                )

                2 -> TabContentScreen(data = "Welcome to Main Screen")

            }
        }
    }

    @Composable
    fun TabContentScreen(data: String) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(
                text = data,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}


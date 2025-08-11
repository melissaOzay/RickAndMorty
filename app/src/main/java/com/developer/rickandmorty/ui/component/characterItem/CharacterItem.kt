package com.developer.rickandmorty.ui.component.characterItem

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import coil.compose.AsyncImage
import com.developer.rickandmorty.R
import com.developer.rickandmorty.features.data.model.CharacterDetailModel
import com.developer.rickandmorty.ui.component.favoritebutton.FavoriteButton
import com.developer.rickandmorty.ui.theme.getMyTypography
import network.chaintech.sdpcomposemultiplatform.sdp

@Composable
fun CharacterItem(
    character: CharacterDetailModel,
    onFavoriteChange: (Boolean) -> Unit = {},
    onCharacterClick: (CharacterDetailModel) -> Unit = {}
) {

    Box(
        modifier = Modifier
            .clickable {
                onCharacterClick(character)
            }
            .fillMaxHeight(0.70f)
            .background(color = Color.Transparent)
            .padding(bottom = 13.sdp, top = 10.sdp, start = 10.sdp, end = 10.sdp),
        contentAlignment = Alignment.BottomStart,

        ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.sdp))
                .background(Color.Gray)

        ) {

            Row(
                modifier = Modifier.padding(bottom = 5.sdp, top = 5.sdp, end = 25.sdp)
            ) {
                Box(modifier = Modifier.fillMaxWidth(0.58f))

                Column(
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = character.name,
                        color = Color.White,
                        style = getMyTypography().titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.sdp))

                    Row {
                        Image(
                            modifier = Modifier.height(18.sdp),
                            painter = when (character.status) {
                                "Alive" -> painterResource(id = R.drawable.ic_heart_custom)
                                "Dead" -> painterResource(id = R.drawable.ic_dead_custom)
                                else -> painterResource(id = R.drawable.ic_question_mark_)
                            },
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.height(6.sdp))
                        Text(
                            modifier = Modifier.height(18.sdp),
                            text = when (character.status) {
                                "Alive" -> "Alive"
                                "Dead" -> "Dead"
                                else -> "Unknown"
                            },
                            color = Color.White,
                            style = getMyTypography().bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.height(6.sdp))
                    Row {
                        Image(
                            modifier = Modifier.height(18.sdp),
                            painter = when (character.gender) {
                                "Male" -> painterResource(id = R.drawable.ic_male)
                                "Female" -> painterResource(id = R.drawable.ic_female)
                                else -> painterResource(id = R.drawable.ic_question_mark_)
                            },
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.height(5.sdp))
                        Text(
                            modifier = Modifier.height(18.sdp),
                            text = when (character.gender) {
                                "Male" -> "Male"
                                "Female" -> "Female"
                                else -> "Unknown"
                            },
                            color = Color.White,
                            style = getMyTypography().bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    FavoriteButton(
                        initialChecked = character.isFavorite,
                        onFavoriteChange = {
                            onFavoriteChange.invoke(it)
                        }
                    )

                    Spacer(modifier = Modifier.height(10.sdp))


                }
            }
        }


        Box(modifier = Modifier.padding(start = 10.sdp, bottom = 20.sdp)) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(18.sdp))
                    .height(140.sdp)
                    .width(120.sdp)
            ) {
                AsyncImage(
                    model = character.image,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

        }


    }
}
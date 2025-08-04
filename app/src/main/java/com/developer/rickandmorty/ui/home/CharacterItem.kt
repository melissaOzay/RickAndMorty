package com.developer.rickandmorty.ui.home

import android.graphics.drawable.Icon
import android.media.Image
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.developer.rickandmorty.R
import com.developer.rickandmorty.domain.model.CharacterDetailModel
import com.developer.rickandmorty.ui.theme.RickAndMortyTheme

@Composable
fun CharacterItem(
    character: CharacterDetailModel,
) {
    Box(
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color = Color.Transparent)
            .padding(bottom = 13.dp),
            contentAlignment = Alignment.BottomStart
    ) {

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .height(140.dp)
                .fillMaxWidth()
                .background(Color.DarkGray)

        ) {

            Row(
                modifier = Modifier.padding(bottom = 5.dp, top = 5.dp)
            ) {
                Box(modifier = Modifier.width(140.dp))

                Column(
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        text = character.name,
                        color = Color.White,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(modifier = Modifier.height(5.dp))

                    Row {
                        Image(
                            modifier = Modifier.height(18.dp),
                            painter = when (character.status) {
                                "Alive" -> painterResource(id = R.drawable.ic_heart_custom)
                                "Dead" -> painterResource(id = R.drawable.ic_dead_custom)
                                else ->painterResource(id = R.drawable.ic_question_mark_)
                            },
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            modifier = Modifier.height(18.dp),
                            text = when (character.status) {
                                "Alive" -> "Alive"
                                "Dead" -> "Dead"
                                else -> "Unknown"
                            },
                            color = Color.White,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Row {
                        Image(
                            modifier = Modifier.height(18.dp),
                            painter = when (character.gender) {
                                "Male" -> painterResource(id = R.drawable.ic_male)
                                "Female" -> painterResource(id = R.drawable.ic_female)
                                else ->painterResource(id = R.drawable.ic_question_mark_)
                            },
                            contentDescription = null,
                        )
                        Spacer(modifier = Modifier.height(5.dp))
                        Text(
                            modifier = Modifier.height(18.dp),
                            text = when (character.gender) {
                                "Male" -> "Male"
                                "Female" -> "Female"
                                else -> "Unknown"
                            },
                            color = Color.White,
                            fontSize = 14.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))


                }
            }
        }


        Box(modifier = Modifier.padding(start = 10.dp, bottom = 20.dp)) {

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(8.dp))
                    .height(180.dp)
                    .width(120.dp)
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

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RickAndMortyTheme {
        CharacterItem(
            CharacterDetailModel(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                gender = "",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                species = "Human",
            )
        )
    }
}
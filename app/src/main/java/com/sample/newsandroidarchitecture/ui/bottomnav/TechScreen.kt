package com.sample.newsandroidarchitecture.ui.bottomnav

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import com.sample.newsandroidarchitecture.model.Article
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@Composable
fun TechScreen(viewModel: TechScreenViewModel) {
    /*Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .wrapContentSize(Alignment.TopStart)
    ) {

        article.map { 
            ArticleItem(article = it)
        }*/
    val article = viewModel.mutableArticleData
    LazyColumn(
        //cells = GridCells.Fixed(2),
        contentPadding = PaddingValues(0.dp, 30.dp),
        content = {
            items(article.size) { index ->
                ArticleItem(
                    article = article[index]
                )
            }
        }
    )
}

@Composable
fun ArticleItem(article: Article) {
    Card(
        elevation = 20.dp,
        backgroundColor = Color.Black,
        modifier =
        Modifier
            .padding(16.dp)
            .clip(RoundedCornerShape(10.dp))
            .height(250.dp)
            .fillMaxWidth()
    ) {

        ConstraintLayout {
            val (image, title, rating) = createRefs()
            Image(
                painter = rememberAsyncImagePainter(article.urlToImage),
                contentDescription = "Image"
            )
            /*Image(
                contentScale = ContentScale.Crop,
                painter = rememberAsyncImagePainter(article.urlToImage)
                contentDescription = "Image",
                modifier =
                Modifier
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .height(150.dp)
                    .fillMaxWidth()
            )*/
            Text(
                text = article.title ?: "",
                color = Color(0xFFF50057),
                maxLines = 2,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier =
                Modifier
                    .constrainAs(image) {
                        top.linkTo(image.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            Row(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .constrainAs(
                        rating
                    ) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            ) {
                Text(
                    text = article.content.toString(),
                    color = Color(0xFFFFC400),
                    modifier = Modifier.padding(8.dp),
                    fontSize = 18.sp
                )
                Image(
                    contentScale = ContentScale.Crop,
                    painter =
                    painterResource(
                        id = com.sample.newsandroidarchitecture.R.drawable.ic_tech
                    ),
                    contentDescription = "Star",
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}
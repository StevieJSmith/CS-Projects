const express = require('express'); // allow for get and post
const cheerio = require('cheerio'); // allow for searching different tags and their contents
const axios = require('axios'); // allow obtaining of html from specific url

const PORT = 9000;

newspapers = [
    {
        name: 'bbc',
        address: 'https://www.bbc.co.uk/news/coronavirus',
        base: 'https://www.bbc.co.uk'
    },
    {
        name: 'telegraph',
        address: 'https://www.telegraph.co.uk/uk-coronavirus-lockdown/',
        base: 'https://www.telegraph.co.uk'
    },
    {
        name: 'sun',
        address: 'https://www.thesun.co.uk/topic/coronavirus/',
        base: ''
    },
    {
        name: 'dailymail',
        address: 'https://www.dailymail.co.uk/news/coronavirus/index.html',
        base: ''
    },
];


const news = [];

const app = express(); // assign app as a new express application


// example of getting infomation from a singular source!!!

// app.get('/news', (req, res) => {
//     axios.get('https://www.bbc.com/news/coronavirus')
//         .then((response) => {
//             const html = response.data;
//             const $ = cheerio.load(html);

//             $('a:contains("Covid")', html).each(() => { // a elements in the html that contains 'covid'
//                 const title = $(this).text(); // a text
//                 const url = $(this).attr('href'); // href text

//                 news.push({
//                     title,
//                     url
//                 });
//             });
//             res.json(news); // response in json format of the news obtained
//         }).catch((err) => {
//             console.log(err);
//         });
// });

//});

newspapers.forEach(newspaper => {
    axios.get(newspaper.address)
        .then(response => {
            const html = response.data; // retrieve website html
            const $ = cheerio.load(html); // load html using Cheerio, so later we can search for specifc tags and contents

            $('a:contains("Covid")', html).each(function () { // a elements in the html that contains 'covid'
                const title = $(this).text().trim(); // title text (trim to remove unwanted text)
                const url = $(this).attr('href'); // href text

                news.push({ // append article object into 'news' array
                    title: title,
                    url: newspaper.base + url,
                    source: newspaper.name
                });
            });
        });
});

app.get('/', (req, res) => { // homepage visit response
    res.json("Welcome to my Covid-19 News API!!! ->  go to the 'news' path to see the latest Covid-19 news! (/news)");
});

app.get('/news', (req, res) => { // get request on news path displaying all articles written about Covid on specific newspapers!!!
    res.json(news);
});

app.get('/news/:newspaperId', async (req, res) => { // get request on news path displaying a specific newpapers articles!!! (query)
     const newspaperId = req.params.newspaperId; // userInput (name of newspaper)
     const newspaperBase = newspapers.filter(newspaper => newspaper.name == newspaperId)[0].base; // if newspaper has a base stored inside object
     const newspaperAddress = newspapers.filter(newspaper => newspaper.name == newspaperId)[0].address;
     // filter to return only newspapers that match the one given by the user! index 0 to select our newspaper object and retrieve the address!

     axios.get(newspaperAddress)
        .then(response => {
            const html = response.data;
            const $ = cheerio.load(html);
            const chosenNews = [];

            $('a:contains("Covid")', html).each(function () { // a elements that contain the word 'Covid'
                const title = $(this).text(); // retrieve title and url
                const url = $(this).attr('href');

                chosenNews.push({ // place new article object inside 'ChosenNews'
                    title: title,
                    url: newspaperBase + url,
                    source: newspaperId
                });
            });
            res.json(chosenNews);
        }).catch((err) => {
            console.log(err);
            });
     
 });

app.listen(PORT, () => { // terminal confirmation
    console.log(`The server is running on PORT ${PORT} !!!`);
});
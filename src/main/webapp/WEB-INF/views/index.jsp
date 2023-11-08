<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <title>Twitter markup</title>
    <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
    
    <link rel="stylesheet" href="/resources/css/global.css" />
    <link rel="stylesheet" href="/resources/css/blocks/layout.css" />
    <link rel="stylesheet" href="/resources/css/blocks/brand.css" />
    <link rel="stylesheet" href="/resources/css/blocks/sidebar-menu.css" />
    <link rel="stylesheet" href="/resources/css/blocks/tweet.css" />
    <link rel="stylesheet" href="/resources/css/blocks/trends-for-you.css" />
    <link rel="stylesheet" href="/resources/css/blocks/who-to-follow.css" />
	<style>
		::selection{
		  color: #fff;
		  background: #1da1f2;
		}
		.wrapper{
		  background: #1da1f2;
		  max-width: 475px;
		  width: 100%;
		  border-radius: 15px;
		  padding:15px 15px 5px 15px;
		  box-shadow: 0px 10px 15px rgba(0,0,0,0.1);
		}
		.input-box{
		  padding-top: 10px;
		  border-bottom: 1px solid #e6e6e6;
		}
		.input-box .tweet-area{
		  position: relative;
		  min-height: 130px;
		  max-height: 170px;
		  overflow-y: auto;
		}
		.tweet-area::-webkit-scrollbar{
		  width: 0px;
		}
		.tweet-area .placeholder{
		  position: absolute;
		  margin-top: -3px;
		  font-size: 22px;
		  color: #98A5B1;
		  pointer-events: none;
		}
		.tweet-area .input{
		  outline: none;
		  font-size: 17px;
		  min-height: inherit;
		  word-wrap: break-word;
		  word-break: break-all;
		}
		.tweet-area .editable{
		  position: relative;
		  z-index: 5;
		}
		.tweet-area .readonly{
		  position: absolute;
		  top: 0;
		  left: 0;
		  z-index: -1;
		  color: transparent;
		  background: transparent;
		}
		.readonly .highlight{
		  background: #fd9bb0;
		}
		.input-box .privacy{
		  color: #1da1f2;
		  margin: 15px 0;
		  display: inline-flex;
		  align-items: center;
		  padding: 7px 10px;
		  border-radius: 50px;
		  cursor: pointer;
		  transition: background 0.2s ease;
		}
		.privacy:hover, .icons li:hover{
		  background: #e7f5fe;
		}
		.privacy i{
		  font-size: 18px;
		}
		.privacy span{
		  font-size: 15px;
		  font-weight: 600;
		  margin-left: 7px;
		}
		.bottom{
		  display: flex;
		  align-items: center;
		  justify-content: space-between;
		}
		.bottom .icons{
		  display: inline-flex;
		}
		.icons li{
		  list-style: none;
		  color: #1da1f2;
		  font-size: 20px;
		  margin: 0 2px;
		  height: 38px;
		  width: 38px;
		  cursor: pointer;
		  display: flex;
		  align-items: center;
		  justify-content: center;
		  border-radius: 50%;
		  transition: background 0.2s ease;
		}
		.bottom .content{
		  display: flex;
		  align-items: center;
		  justify-content: center;
		}
		.bottom .counter{
		  color: #333;
		  display: none;
		  font-weight: 500;
		  margin-right: 15px;
		  padding-right: 15px;
		  border-right: 1px solid #aab8c2;
		}
		
		.bottom button.active{
		  opacity: 1;
		  pointer-events: auto;
		}
		.bottom button:hover{
		  background: #0d8bd9;
		}
		
		
	.new_tweet {
		display: inline-block;
		outline: 0;
		border: 0;
		cursor: pointer;
		will-change: box-shadow,transform;
		background: radial-gradient( 100% 100% at 100% 0%, #89E5FF 0%, #5468FF 100% );
		box-shadow: 0px 2px 4px rgb(45 35 66 / 40%), 0px 7px 13px -3px rgb(45 35 66 / 30%), inset 0px -3px 0px rgb(58 65 111 / 50%);
		padding: 0 32px;
		border-radius: 6px;
		color: #fff;
		height: 48px;
		font-size: 18px;
		text-shadow: 0 1px 0 rgb(0 0 0 / 40%);
		transition: box-shadow 0.15s ease,transform 0.15s ease;
		margin: 0 auto;
	}

	.new_tweet:hover {
	  box-shadow: 0px 4px 8px rgb(45 35 66 / 40%), 0px 7px 13px -3px rgb(45 35 66 / 30%), inset 0px -3px 0px #3c4fe0;
						transform: translateY(-2px);
	}
	.new_tweet:active {
		box-shadow: inset 0px 3px 7px #3c4fe0;
		transform: translateY(2px);
	}
	#create_tweet_button {
		text-align:center;
	}

	</style>
		<script>
			function submitTweet() {
				document.getElementById('realTweetContent').value = document.getElementById('tweetContent').innerHTML;
			  document.getElementById("tweetForm").submit();
			}
			
			$(document).ready(function(){
				$(".wrapper").hide();
				$(".new_tweet").click(function(){
					$(".wrapper").show();
					$(".new_tweet").hide();
				}); 
			});
		</script>
  </head>
  <body>
    <div class="layout">
      <div class="layout__left-sidebar">
        <img src="/resources/svg/accelerate-svgrepo-com.svg" class="brand" />
        <div class="sidebar-menu">
          <div class="sidebar-menu__item sidebar-menu__item--active">
            <img src="/resources/svg/home.svg" class="sidebar-menu__item-icon" />
            Home
          </div>
          
          <div class="sidebar-menu__item">
            <img src="/resources/svg/profile.svg" class="sidebar-menu__item-icon" />
            Profile
          </div>

          <div class="sidebar-menu__item">
            <img src="/resources/svg/more.svg" class="sidebar-menu__item-icon" />
            <a href="/logout">Logout</a>
          </div>
        </div>
      </div>
      <div class="layout__main">
       <div id="create_tweet_button">
			<button class="new_tweet">Create new tweet</button>
	    </div>
       <div class="wrapper">
		<form id="tweetForm" method="POST" action="/createtweet">
			<div class="input-box">
			  <div class="tweet-area">
			   
				<span class="placeholder">What's happening?</span>
				<div id="tweetContent" class="input editable" contenteditable="true" spellcheck="false"></div>
				 <input id="realTweetContent" style="display: none" type="text" name="content">
				<div class="input readonly" contenteditable="true" spellcheck="false"></div>
				</form>
			  </div>
			</div>
			 <div class="bottom">
			  <ul class="icons">
				<li><i class="uil uil-capture"></i></li>
				<li><i class="far fa-file-image"></i></li>
				<li><i class="fas fa-map-marker-alt"></i></li>
				<li><i class="far fa-grin"></i></li>
				<li><i class="far fa-user"></i></li>
			  </ul>
			  <div class="content">
				<span class="counter">100</span>
				<button onclick="submitTweet()">Tweet</button>
			  </div>
			</div>
			</form>
		  </div>
		  <c:forEach var="tweet" items="${tweets}">
		        <div class="tweet">
		          <img alt="prof_pic" class="tweet__author-logo" src="/resources/images/icons8-admin.png" />
		          <div class="tweet__main">
		            <div class="tweet__header">
		              <div class="tweet__author-name">${tweet.username}</div>
		              <div class="tweet__author-slug">@${tweet.username}</div>
		              <div class="tweet__publish-time">${tweet.timestamp}</div>
		            </div>
		            <div class="tweet__content">${tweet.content}</div>
		          </div>
		        </div>
        </c:forEach>
       
      </div>
      <div class="layout__right-sidebar-container">

        </div>
      </div>
    </div>
  </body>
  <script src="/resources/js/tweet-script.js"></script>
</html>

<!-- 侧边栏 开始-->
<!-- 热门文章 -->
<div class="qing-margin-bottom">
	<div class="qing-panel">
		<div class="qing-panel-title">
			<h2>热门文章</h2>
		</div>
		<div class="qing-panel-body" >
			<@blogList type="views">
				<#list list as x>
					<div class="qing-item-list">
						<a class="qing-item-link" href="${ctx!}/b/view/${x.id}">${x.title}</a> 
						<span>${x.views}阅</span>
					</div>
				</#list>
			</@blogList>
		</div>
	</div>
</div>
<!-- 推荐文章 -->
<div class="qing-margin-bottom">
	<div class="qing-panel">
		<div class="qing-panel-title">
			<h2>推荐文章</h2>
		</div>
		<div class="qing-panel-body" >
			<@blogList type="featured">
				<#list list as x>
					<div class="qing-item-list">
						<a class="qing-item-link" href="${ctx!}/b/view/${x.id}">${x.title}</a> 
						<span>${x.views}阅</span>
					</div>
				</#list>
			</@blogList>
		</div>
	</div>
</div>
<!-- 热门标签 -->
<div class="qing-margin-bottom">
	<div class="qing-panel">
		<div class="qing-panel-title">
			<h2>标签分类</h2>
		</div>
		<div class="qing-panel-body">
			<div id="blog-category">
			<@tagList>
				<#list list as x>
					<a href="${ctx!}/t/${x.name}">
						<span class="qing-left-category qing-left-category${x_index%5} am-radius">${x.name}(${x.count})</span>
					</a> 
				</#list>
			</@tagList>
			</div>
		</div>
	</div>
</div>
<!-- 最新评论 -->
<div class="qing-margin-bottom">
	<div class="qing-panel">
		<div class="qing-panel-title">
			<h2>最新评论</h2>
		</div>
		<div class="qing-panel-body">
			<!-- 多说最新评论 start -->
			<div class="ds-recent-comments" data-num-items="10" data-show-avatars="1" data-show-time="1" data-show-title="1" data-show-admin="1" data-excerpt-length="70"></div>
			<!-- 多说最新评论 end -->
		</div>
	</div>
</div>
<!-- 友情链接 -->
<div class="qing-margin-bottom">
 	<div class="qing-panel">
 	  	<div class="qing-panel-title">
 	  		<h2>友情链接</h2>
 	  	</div>
 	  	<div class="qing-panel-body">
 	  		<ul>
<!--             	<li><a href="" target="_blank">{{item.name}}</a></li> -->
          	</ul>
 	  	</div>
 	</div>
 </div>
<!-- 侧边栏结束-->
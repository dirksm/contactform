      <ul class="sidebar-menu">
        <li class="header">NAVIGATION</li>
        <!-- Optionally, you can add icons to the links -->
        <li class=" active"><a href="${pageContext.request.contextPath}/"><i class="fa fa-dashboard"></i> <span>Dashboard</span></a></li>
        <li class="treeview">
          <a href="#">
              <i class="fa fa-users"></i> <span>Customers</span><i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu">
                <li><a href="${pageContext.request.contextPath}/customers/"><i class="fa fa-users"></i> View Customers</a></li>
                <li><a href="${pageContext.request.contextPath}/customers/create/"><i class="fa fa-file-o"></i> Add Customer</a></li>
          </ul>
        </li>
        <li class="treeview">
            <a href="#">
                <i class="fa fa-users"></i> <span>Contacts</span> <i class="fa fa-angle-left pull-right"></i>
            </a>
            <ul class="treeview-menu">
                <li class="active"><a href="${pageContext.request.contextPath}/contacts/"><i class="fa fa-users"></i> View Contacts</a></li>
                <li><a href="${pageContext.request.contextPath}/contacts/create/"><i class="fa fa-file-o"></i> Add Contact</a></li>
            </ul>
        </li>
        <li class="treeview">
            <a href="#">
                <i class="fa fa-google"></i> <span>Addresses</span> <i class="fa fa-angle-left pull-right"></i>
            </a>
            <ul class="treeview-menu">
                <li><a href="${pageContext.request.contextPath}/addresses/"><i class="fa fa-envelope"></i> View Addresses</a></li>
                <li><a href="${pageContext.request.contextPath}/addresses/create/"><i class="fa fa-file-o"></i> Add Address</a></li>
            </ul>
        </li>
        <li class="treeview">
          <a href="#">
            <i class="fa fa-files-o"></i>
            <span>Layout Options</span><i class="fa fa-angle-left pull-right"></i>
          </a>
          <ul class="treeview-menu" id="layout-skins-list">
            <li><a href="#" data-skin="skin-blue"><i class="fa fa-circle-o text-aqua pull-right"> </i> Blue</a></li>
            <li><a href="#" data-skin="skin-blue-light"><i class="fa fa-circle-o text-aqua pull-right"> </i> Blue-Light</a></li>
            <li><a href="#" data-skin="skin-purple"><i class="fa fa-circle-o text-purple pull-right"> </i> Purple</a></li>
            <li><a href="#" data-skin="skin-purple-light"><i class="fa fa-circle-o text-purple pull-right"> </i> Purple-Light</a></li>
            <li><a href="#" data-skin="skin-yellow"><i class="fa fa-circle-o text-yellow pull-right"> </i> Yellow</a></li>
            <li><a href="#" data-skin="skin-yellow-light"><i class="fa fa-circle-o text-yellow pull-right"> </i> Yellow-Light</a></li>
            <li><a href="#" data-skin="skin-green"><i class="fa fa-circle-o text-green pull-right"> </i> Green</a></li>
            <li><a href="#" data-skin="skin-green-light"><i class="fa fa-circle-o text-green pull-right"> </i> Green-Light</a></li>
            <li><a href="#" data-skin="skin-red"><i class="fa fa-circle-o text-red pull-right"> </i> Red</a></li>
            <li><a href="#" data-skin="skin-red-light"><i class="fa fa-circle-o text-red pull-right"> </i> Red-Light</a></li>
            <li><a href="#" data-skin="skin-black"><i class="fa fa-circle-o text-black pull-right"> </i> Black</a></li>
            <li><a href="#" data-skin="skin-black-light"><i class="fa fa-circle-o text-black pull-right"> </i> Black-Light</a></li>
          </ul>
        </li>
      </ul>

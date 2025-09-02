<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Profile</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css"
        crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css"
        crossorigin="anonymous" referrerpolicy="no-referrer" />
<style>
    .upload-box { 
    border: 2px dashed #6c757d; 
    border-radius: 10px; 
    padding: 40px; 
    text-align: center; 
    cursor: pointer; 
    background: #f8f9fa; 
    transition: border-color 0.3s, background 0.3s; 
    }
    .upload-box.dragover { 
    border-color: #0d6efd; 
    background: #e9f5ff; 
    }
    .preview img { 
    max-width: 100%; 
    height: auto; 
    margin-top: 15px; 
    border-radius: 10px; 
    box-shadow: 0px 4px 6px rgba(0,0,0,0.1);
    }
</style>
</head>
<body>

<div class="container mt-3">

    <f:form modelAttribute="user"
            method="post"
            action="${pageContext.request.contextPath}/user/profile/update"
            enctype="multipart/form-data">
            
            <!-- onsubmit="updateSkillIndexes();" -->
    
    
	<f:hidden path="id"/>

       
        <div class="border border-secondary mt-3 p-3">

            <div class="row align-items-center">

                <!-- Profile Image -->
                <div class="col-3 d-flex flex-column align-items-center">
                    <label for="fileInput">Profile Photo (JPEG)</label>
                         
					     
					     <!-- Clickable Image -->
						<img id="userImage"
							 src="${pageContext.request.contextPath}/user/photo/${user.id}"
						     class="border border-secondary mb-3"
						     style="cursor:pointer; border-radius:50%; width:150px; height:150px; object-fit:cover;"
						     onerror="this.onerror=null;" />
						
						<!-- Hidden File Input -->
						<input type="file" id="fileInput" name="photoFile" accept="image/*" style="display:none" />
					     
					     
					     

                    
                    <div class="w-100">
                        <label>Country</label>
                        <f:input type="text" class="form-control" path="userDetails.location"/>
                    </div>
                </div>

                <!-- User Info Form -->
                <div class="col-9">
                    <div class="form-group row">
                        <div class="col">
                            <label for="firstName">First Name</label>
                            <f:input type="text" class="form-control" path="firstName"/>
                        </div>
                        <div class="col">
                            <label for="lastName">Last Name</label>
                            <f:input type="text" class="form-control" path="lastName"/>
                        </div>
                    </div>

                    <div class="mb-3">
                        <label>Bio</label>
                        <f:textarea class="form-control" placeholder="Bio" cols="12" rows="5" path="userDetails.bio"/>
                    </div>
                </div>
            </div>

            <hr class="border">

            <!-- Skills -->
            <h3 class="border-bottom">Skills:</h3>
            
            
            
            
           <!-- Offered Skills -->
		<div class="mb-3"></div>
        <h4 class="mt-4">Offered Skills:</h4>
        
        
         <div class="d-flex flex-column">
        
         <c:forEach var="offered" items="${offeredSkills}">
             <input type="hidden" name="offeredSkillRowIds" value="${offered.id}" />
         
		    <div class="d-flex align-items-center mb-2 skill-row">
		
		        <!-- Skill select -->
		        <select class="form-control w-25 mx-2" name="skillIds">
		            <option value="" ${offered.skill == null ? 'selected' : ''}>Select Skill</option>
		            <c:forEach var="skill" items="${skills}">
		                <option value="${skill.id}" ${offered.skill != null && skill.id == offered.skill.id ? 'selected' : ''}>
		                    ${skill.skillName}
		                </option>
		            </c:forEach>
		        </select>
		
		        <!-- Level select -->
		        <select class="form-control w-25 mx-2" name="skillLevels">
		            <option value="" ${offered.level == null ? 'selected' : ''}>Select Level</option>
		            <option value="Beginner" ${"Beginner".equals(offered.level) ? 'selected' : ''}>Beginner</option>
		            <option value="Intermediate" ${"Intermediate".equals(offered.level) ? 'selected' : ''}>Intermediate</option>
		            <option value="Advanced" ${"Advanced".equals(offered.level) ? 'selected' : ''}>Advanced</option>
		        </select>
		
		    </div>
		</c:forEach>


        
         </div>
              
            <!-- Wanted Skills -->
      <div class="mb-3">
      
        <h4 class="mt-4">Wanted Skills:</h4>
        
         <div class="d-flex flex-column">
         
		<c:forEach var="wanted" items="${wantedSkills}">
		    <div class="d-flex align-items-center mb-2 skill-row">
		        <input type="hidden" name="wantedSkillRowIds" value="${wanted.id != null ? wanted.id : ''}">
		        <select class="form-control w-25 mx-2" name="wantedSkillIds">
		            <option value="" disabled <c:if test="${wanted.skill == null}">selected</c:if>>Select Skill</option>
		            <c:forEach var="skill" items="${skills}">
		                <option value="${skill.id}" <c:if test="${wanted.skill != null && wanted.skill.id == skill.id}">selected</c:if>>
		                    ${skill.skillName}
		                </option>
		            </c:forEach>
		        </select>
		    </div>
		</c:forEach>








            
            
         </div>
         
        
      
      
        
      </div>

      <!-- زر الحفظ -->
      <div class="d-flex justify-content-end mt-3">
        <button type="submit" class="btn btn-primary px-4">Save</button>
      </div>
    </f:form>
  </div>
            
            

            <hr class="border">
            <div class="my-4">
                <h4>Delete Account</h4>
                <p class="text-muted">Once you delete your account, all your data will be permanently removed. This action cannot be undone.</p>
                <div class="d-flex justify-content-end">
                    <a href="${pageContext.request.contextPath}/user/delete" class="btn btn-danger mt-2 mr-4"><i class="fa fa-trash me-2"></i> Delete Account</a>
                </div>
            </div>
            
           

       

<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>


<script>
  const img = document.getElementById("userImage");
  const fileInput = document.getElementById("fileInput");

  
  img.addEventListener("click", () => {
    fileInput.click();
  });

  
  fileInput.addEventListener("change", () => {
    if (fileInput.files && fileInput.files[0]) {
      const reader = new FileReader();
      reader.onload = (e) => {
        img.src = e.target.result; 
      };
      reader.readAsDataURL(fileInput.files[0]);
    }
  });
  
  
  
  document.addEventListener("DOMContentLoaded", function() {
	    document.addEventListener("click", function(e) {
	        // Check if the click was on .removeSkill or inside it
	        const btn = e.target.closest(".removeSkill");
	        if (btn) {
	            const row = btn.closest(".skill-row");
	            if (row) {
	                row.querySelectorAll("select").forEach(select => {
	                    select.selectedIndex = 0; // reset selects
	                });
	            }
	        }
	    });
	});
  
</script>




	
	

</body>
</html>

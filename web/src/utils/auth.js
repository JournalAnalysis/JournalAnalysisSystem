export function urlVerify(arr) { 
    let route = false
    store.getters.roles.forEach(res=>{//store.getters.roles是我判断当前用户的权限字符
      if(arr.includes(res)){
        route = true
      }
    })
    if(route){
      return true
    }else{
      return false
    }
}
import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {FootballApiService} from "../../_services/football-api.service";
import {CommentService} from "../../_services/comment.service";
import {Comment} from "../../_models/comment";
import Swal from "sweetalert2";

@Component({
  selector: 'app-football-match-comments',
  templateUrl: './football-match-comments.component.html',
  styleUrl: './football-match-comments.component.css'
})
export class FootballMatchCommentsComponent {

  comments!: Comment[];
  inputValue = '';
  matchId!: number;

  constructor(private router: Router, private commentService: CommentService, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.matchId = params['matchId'];
      this.commentService.getCommentsByMatchId(params['matchId'], "FOOTBALL")
        .then(response => {
          this.comments = response;
        })
    })
  }

  async addComment() {
    if (window.localStorage.getItem("auth_token") === null || window.localStorage.getItem("user_id") == "") {
      Swal.fire({
        title: "İşlem Başarısız",
        html: "Sisteme Giriş Yapmalısınız...",
        icon: 'error',
      })
    }
    const { value: comment } = await Swal.fire({
      title: "Yorumunuzu Girin",
      input: "textarea",
      inputLabel: "",
      showCancelButton: true,
      inputValidator: (value) => {
        if (!value) {
          return "Alan boş bırakılamaz!";
        }
        return null;
      }
    });
    if (comment) {
      this.commentService.createComment(parseInt(window.localStorage.getItem("user_id")!), parseInt(String(this.matchId)), comment, "FOOTBALL")
        .then(response => {
          if (response.status === 200) {
            Swal.fire({
              title: "İşlem Başarılı",
              html: "Yorumunuz Kayıt Edildi",
              icon: 'success',
              timer: 3000,
              timerProgressBar: true,
            })
          }
        })
        .catch(error => {
          Swal.fire({
            title: "İşlem Başarısız",
            html: "Yorumunuz Kayıt Edilemedi",
            icon: 'error',
          })
        })

    }
    this.inputValue = comment;
  }
}
